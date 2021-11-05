package de.charlex.settings.datastore.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.KEY_ALGORITHM_AES
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object Security {

    val securityKeyAlias = "data-store"
    val ivLength = 12 // in bytes
    val tagLength = 128 // in bits

    private val provider = "AndroidKeyStore"

    private val charset by lazy {
        charset("UTF-8")
    }
    private val keyStore by lazy {
        KeyStore.getInstance(provider).apply {
            load(null)
        }
    }
    private val keyGenerator by lazy {
        KeyGenerator.getInstance(KEY_ALGORITHM_AES, provider)
    }

    private fun createCipher() = Cipher.getInstance("$KEY_ALGORITHM_AES/$BLOCK_MODE_GCM/$ENCRYPTION_PADDING_NONE")

    fun encryptData(keyAlias: String, text: String): Pair<ByteArray, ByteArray> {
        val secretKey = getSecretKey(keyAlias) ?: generateSecretKey(keyAlias)
        val cipher = createCipher()
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv.copyOf()
        val ciphertext = cipher.doFinal(text.toByteArray(charset))
        return iv to ciphertext
    }

    fun decryptData(keyAlias: String, iv: ByteArray, encryptedData: ByteArray): String {
        val secretKey = getSecretKey(keyAlias)
        val cipher = createCipher()
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(tagLength, iv))
        return cipher.doFinal(encryptedData).toString(charset)
    }

    fun extractIvAndCipherText(encryptedText: String): Pair<ByteArray, ByteArray>? =
        try {
            val combined = encryptedText.decodeBase64()
            val iv = combined.copyOfRange(0, ivLength)
            val ciphertext = combined.copyOfRange(ivLength, combined.size)
            iv to ciphertext
        } catch (t: Throwable) {
            if (BuildConfig.DEBUG) {
                t.printStackTrace()
            }
            null
        }

    fun joinIvAndCipherText(iv: ByteArray, ciphertext: ByteArray): String =
        (iv + ciphertext).encodeBase64()

    private fun generateSecretKey(keyAlias: String): SecretKey {
        return keyGenerator.apply {
            init(
                KeyGenParameterSpec
                    .Builder(keyAlias, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
                    .setBlockModes(BLOCK_MODE_GCM)
                    .setEncryptionPaddings(ENCRYPTION_PADDING_NONE)
                    .build()
            )
        }.generateKey()
    }

    private fun getSecretKey(keyAlias: String): SecretKey? =
        (keyStore.getEntry(keyAlias, null) as KeyStore.SecretKeyEntry?)?.secretKey

    private fun ByteArray.encodeBase64(): String = Base64.encodeToString(this, Base64.DEFAULT)

    private fun String.decodeBase64(): ByteArray = Base64.decode(this, Base64.DEFAULT)
}
