package de.charlex.settings.datastore.encryption

import androidx.datastore.core.CorruptionException
import de.charlex.settings.datastore.SettingsDataStore
import de.charlex.settings.datastore.stringPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val json = Json { encodeDefaults = true }

inline fun <reified T> SettingsDataStore.get(pref: IDataStoreEncryptedPreference<T>): Flow<T> {
    val rawValue = this.get(stringPreference(pref.preferenceKey.name, "NULL"))
    return rawValue.map {
        if (it == "NULL") {
            pref.defaultValue
        } else {
            decrypt(it)
        }
    }.catch { e ->
        throw CorruptionException("Invalid data stored in Preference: ${pref.preferenceKey.name}", e)
    }
}

inline fun <reified T> decrypt(encrypted: String): T {
    val (iv, cipherText) = Security.extractIvAndCipherText(encrypted) ?: error("Invalid data stored")
    val decryptedValue = Security.decryptData(
        Security.securityKeyAlias,
        iv,
        cipherText
    )
    return try {
        json.decodeFromString(decryptedValue)
    } catch (exception: Exception) {
        throw ClassCastException()
    }
}

inline fun <reified T> encrypt(value: T): String {
    val (iv, ciphertext) = Security.encryptData(Security.securityKeyAlias, Json.encodeToString(value))
    return Security.joinIvAndCipherText(iv, ciphertext)
}

suspend inline fun <reified T> SettingsDataStore.put(pref: IDataStoreEncryptedPreference<T>, value: T) {
    val encrypted = encrypt(value)
    this.put(stringPreference(pref.preferenceKey.name, "NULL"), encrypted)
}

suspend inline fun <reified T> SettingsDataStore.remove(pref: IDataStoreEncryptedPreference<T>) {
    this.remove(stringPreference(pref.preferenceKey.name, "NULL"))
}
