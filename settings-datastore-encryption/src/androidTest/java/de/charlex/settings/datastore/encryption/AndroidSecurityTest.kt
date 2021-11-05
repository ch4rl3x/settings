package de.charlex.settings.datastore.encryption

import org.junit.Test

open class AndroidSecurityTest {

    /**
     * Cipher is not thread-safe so it has to be used correctly to avoid race conditions.
     * This would throw exceptions when cipher.doFinal / cipher.init is used concurrently
     */
    @Test
    fun cipher_noRaceCondition() {
        val (iv, cipherText) = Security.encryptData("testAlias", "secret")
        (1..100).toList().parallelStream().map {
            Security.decryptData("testAlias", iv, cipherText)
        }.count()
    }
}
