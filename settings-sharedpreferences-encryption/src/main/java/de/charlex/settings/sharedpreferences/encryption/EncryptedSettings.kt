package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import de.charlex.settings.sharedpreferences.Keyed
import de.charlex.settings.sharedpreferences.Settings

interface EncryptedSettings {

    fun getRaw(key: String): String?
    fun getString(pref: IEncryptedSharedPreference<String>): String
    fun getInt(pref: IEncryptedSharedPreference<Int>): Int
    fun getFloat(pref: IEncryptedSharedPreference<Float>): Float
    fun getDouble(pref: IEncryptedSharedPreference<Double>): Double
    fun getBoolean(pref: IEncryptedSharedPreference<Boolean>): Boolean
    fun getLong(pref: IEncryptedSharedPreference<Long>): Long
    fun getStringSet(pref: IEncryptedSharedPreference<Set<String>>): Set<String>

    fun putString(pref: IEncryptedSharedPreference<String>, value: String)
    fun putInt(pref: IEncryptedSharedPreference<Int>, value: Int)
    fun putFloat(pref: IEncryptedSharedPreference<Float>, value: Float)
    fun putDouble(pref: IEncryptedSharedPreference<Double>, value: Double)
    fun putBoolean(pref: IEncryptedSharedPreference<Boolean>, value: Boolean)
    fun putLong(pref: IEncryptedSharedPreference<Long>, value: Long)
    fun <T> putEnum(pref: IEncryptedSharedPreference<T>, value: T) where T : Enum<T>, T : Keyed
    fun putStringSet(pref: IEncryptedSharedPreference<Set<String>>, value: Set<String>)
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

inline fun <reified T> EncryptedSettings.getEnum(pref: IEncryptedSharedPreference<T>): T where T : Enum<T>, T : Keyed {
    val enumKey = getRaw(pref.preferenceKey)
    return enumValues<T>().find { it.key == enumKey } ?: pref.defaultValue
}
