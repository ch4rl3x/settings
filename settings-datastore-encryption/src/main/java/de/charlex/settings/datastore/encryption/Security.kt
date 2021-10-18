package de.charlex.settings.datastore.encryption

import android.R.attr
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.*
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import android.R.attr.tag




object Security {

    val securityKeyAlias = "data-store"
    val bytesToStringSeparator = "|"

    private val provider = "AndroidKeyStore"

    private val cipher by lazy {
        Cipher.getInstance("AES/GCM/NoPadding")
    }
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

    fun encryptData(keyAlias: String, text: String): Pair<ByteArray, ByteArray> {
        val secretKey = getSecretKey(keyAlias) ?: generateSecretKey(keyAlias)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv.copyOf()
        val ciphertext = cipher.doFinal(text.toByteArray(charset))
        return iv to ciphertext
    }

    fun decryptData(keyAlias: String, iv: ByteArray, encryptedData: ByteArray): String {
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(keyAlias), GCMParameterSpec(128, iv))
        return cipher.doFinal(encryptedData).toString(charset)
    }

    fun extractIvAndCipherText(encryptedText: String): Pair<ByteArray, ByteArray>? {
        val parts = encryptedText.split("$bytesToStringSeparator$bytesToStringSeparator")
        if(parts.size != 2) return null
        val iv = parts[0].split(bytesToStringSeparator).map { it.toByte() }.toByteArray()
        val ciphertext = parts[1].split(bytesToStringSeparator).map { it.toByte() }.toByteArray()
        return iv to ciphertext
    }

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

}