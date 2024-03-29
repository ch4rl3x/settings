package de.charlex.settings.datastore.encryption

import android.os.Build
import de.charlex.settings.datastore.security.AESSecurity
import org.junit.Test
import java.util.concurrent.CountDownLatch

open class AndroidSecurityTest {

    /**
     * Cipher is not thread-safe so it has to be used correctly to avoid race conditions.
     * This would throw exceptions when cipher.doFinal / cipher.init is used concurrently
     */
    @Test
    fun cipher_noRaceCondition() {
        val encryptedText = AESSecurity.encryptData("secret")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            // use old-fashioned threads
            val numThreads = 10
            val countDown = CountDownLatch(numThreads)
            (0..numThreads).toList().map {
                object : Thread() {
                    override fun run() {
                        countDown.countDown()
                        countDown.await()
                        (0..10).toList().map {
                            AESSecurity.decryptData(encryptedText)
                        }
                    }
                }
            }.map {
                it.apply { start() }
            }.map {
                it.join()
            }
        } else {
            // use parallelStream
            (1..100).toList().parallelStream().map {
                AESSecurity.decryptData(encryptedText)
            }.count()
        }
    }
}
