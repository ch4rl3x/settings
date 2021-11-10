package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import de.charlex.settings.sharedpreferences.Settings

interface EncryptedSettings {
    fun <T> get(pref: IEncryptedSharedPreference<T>): T
    fun <T> put(pref: IEncryptedSharedPreference<T>, value: T)
    fun <T : Enum<T>, U> put(pref: IEncryptedEnumSharedPreference<T, U>, value: T)

    fun <T> remove(pref: IEncryptedSharedPreference<T>)
    fun <T : Enum<T>, U> remove(pref: IEncryptedEnumSharedPreference<T, U>)
}

fun Settings.Companion.createEncrypted(
    context: Context,
    name: String = context.packageName + "_preferences",
    mainKeyAlias: String = "sharedPreferences",
    prefKeyEncryptionScheme: EncryptedSharedPreferences.PrefKeyEncryptionScheme = EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    prefValueEncryptionScheme: EncryptedSharedPreferences.PrefValueEncryptionScheme = EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
): EncryptedSettings {
    return EncryptedSettingsImpl(
        context = context,
        name = name + "_encrypted",
        mainKeyAlias = mainKeyAlias,
        prefKeyEncryptionScheme = prefKeyEncryptionScheme,
        prefValueEncryptionScheme = prefValueEncryptionScheme
    )
}

fun Settings.Companion.createInMemoryEncrypted(): EncryptedSettings {
    return EncryptedSettingsInMemoryImpl()
}

inline fun <reified T : Enum<T>, U> EncryptedSettings.get(pref: IEncryptedEnumSharedPreference<T, U>): T {
    val keyProperty = pref.keyProperty
    val sharedPreference = when (val defaultValue = keyProperty.call(pref.defaultValue)) {
        is String -> encryptedStringPreference(pref.preferenceKey, defaultValue as String)
        is Int -> encryptedIntPreference(pref.preferenceKey, defaultValue as Int)
        is Float -> encryptedFloatPreference(pref.preferenceKey, defaultValue as Float)
        is Long -> encryptedLongPreference(pref.preferenceKey, defaultValue as Long)
        is Boolean -> encryptedBooleanPreference(pref.preferenceKey, defaultValue as Boolean)
        else -> error("No valid enum key: $defaultValue")
    }
    val enumKey = get(sharedPreference)
    return enumValues<T>().find { pref.keyProperty.call(it) == enumKey } ?: pref.defaultValue
}
