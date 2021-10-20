package de.charlex.settings.datastore.encryption

import de.charlex.settings.core.IPreference
import de.charlex.settings.core.Keyed
import de.charlex.settings.datastore.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val json = Json { encodeDefaults = true }

inline fun <reified T> SettingsDataStore.get(pref: de.charlex.settings.core.IEncryptedPreference<T>): Flow<T> = getRaw(pref.preferenceKey).map {
    if (it == null) {
        pref.defaultValue
    } else {
        val (iv, cipherText) = Security.extractIvAndCipherText(it) ?: error("Invalid data stored in ${pref.preferenceKey}")
        val decryptedValue = Security.decryptData(
            Security.securityKeyAlias,
            iv,
            cipherText
        )
        try {
            json.decodeFromString(decryptedValue)
        } catch (exception: Exception) {
            throw ClassCastException()
        }
    }
}

inline fun <reified T> SettingsDataStore.getEnum(pref: de.charlex.settings.core.IEncryptedEnumPreference<T>): Flow<T>  where T : Enum<T>, T : Keyed = getRaw(pref.preferenceKey).map {
    if (it == null) {
        pref.defaultValue
    } else {
        val (iv, cipherText) = Security.extractIvAndCipherText(it) ?: error("Invalid data stored in ${pref.preferenceKey}")
        val decryptedValue = Security.decryptData(
            Security.securityKeyAlias,
            iv,
            cipherText
        )
        try {
            json.decodeFromString(decryptedValue)
        } catch (exception: Exception) {
            throw ClassCastException()
        }
    }
}

inline fun <reified T> SettingsDataStore.get(pref: de.charlex.settings.core.IEncryptedEnumPreference<T>): Flow<T>  where T : Enum<T>, T : Keyed = getRaw(pref.preferenceKey).map {
    if (it == null) {
        pref.defaultValue
    } else {
        val (iv, cipherText) = Security.extractIvAndCipherText(it) ?: error("Invalid data stored in ${pref.preferenceKey}")
        val decryptedValue = Security.decryptData(
            Security.securityKeyAlias,
            iv,
            cipherText
        )
        try {
            json.decodeFromString(decryptedValue)
        } catch (exception: Exception) {
            throw ClassCastException()
        }
    }
}

suspend inline fun <reified T> SettingsDataStore.put(pref: de.charlex.settings.core.IEncryptedPreference<T>, value: T) {
    val (iv, ciphertext) = Security.encryptData(Security.securityKeyAlias, Json.encodeToString(value))
    putRaw(
        key = pref.preferenceKey,
        value = "${iv.joinToString(Security.bytesToStringSeparator)}${Security.bytesToStringSeparator}${Security.bytesToStringSeparator}${ciphertext.joinToString(Security.bytesToStringSeparator)}"
    )
}

suspend inline fun <reified T> SettingsDataStore.putEnum(pref: de.charlex.settings.core.IEncryptedEnumPreference<T>, value: T) where T : Enum<T>, T : Keyed {
    val (iv, ciphertext) = Security.encryptData(Security.securityKeyAlias, Json.encodeToString(value.key))
    putRaw(
        key = pref.preferenceKey,
        value = "${iv.joinToString(Security.bytesToStringSeparator)}${Security.bytesToStringSeparator}${Security.bytesToStringSeparator}${ciphertext.joinToString(Security.bytesToStringSeparator)}"
    )
}

suspend inline fun <reified T> SettingsDataStore.put(pref: de.charlex.settings.core.IEncryptedEnumPreference<T>, value: T) where T : Enum<T>, T : Keyed {
    val (iv, ciphertext) = Security.encryptData(Security.securityKeyAlias, Json.encodeToString(value.key))
    putRaw(
        key = pref.preferenceKey,
        value = "${iv.joinToString(Security.bytesToStringSeparator)}${Security.bytesToStringSeparator}${Security.bytesToStringSeparator}${ciphertext.joinToString(Security.bytesToStringSeparator)}"
    )
}