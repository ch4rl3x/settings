package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import de.charlex.settings.sharedpreferences.Keyed
import de.charlex.settings.sharedpreferences.Settings

interface EncryptedSettings {

    fun getRaw(key: String): String?
    fun getString(pref: IEncryptedPreference<String>): String
    fun getInt(pref: IEncryptedPreference<Int>): Int
    fun getFloat(pref: IEncryptedPreference<Float>): Float
    fun getDouble(pref: IEncryptedPreference<Double>): Double
    fun getBoolean(pref: IEncryptedPreference<Boolean>): Boolean
    fun getLong(pref: IEncryptedPreference<Long>): Long

    fun putString(pref: IEncryptedPreference<String>, value: String)
    fun putInt(pref: IEncryptedPreference<Int>, value: Int)
    fun putFloat(pref: IEncryptedPreference<Float>, value: Float)
    fun putDouble(pref: IEncryptedPreference<Double>, value: Double)
    fun putBoolean(pref: IEncryptedPreference<Boolean>, value: Boolean)
    fun putLong(pref: IEncryptedPreference<Long>, value: Long)
    fun <T> putEnum(pref: IEncryptedPreference<T>, value: T) where T : Enum<T>, T : Keyed
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

inline fun <reified T> EncryptedSettings.getEnum(pref: IEncryptedPreference<T>): T where T : Enum<T>, T : Keyed {
    val enumKey = getRaw(pref.preferenceKey)
    return enumValues<T>().find { it.key == enumKey } ?: pref.defaultValue
}
