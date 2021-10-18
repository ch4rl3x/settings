package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import de.charlex.settings.core.IEncryptedPreference
import de.charlex.settings.core.IEncryptedPreferenceValue
import de.charlex.settings.sharedpreferences.Settings

interface EncryptedSettings {
    fun getString(pref: IEncryptedPreference<String>): String
    fun getInt(pref: IEncryptedPreference<Int>): Int
    fun getFloat(pref: IEncryptedPreference<Float>): Float
    fun getDouble(pref: IEncryptedPreference<Double>): Double
    fun getBoolean(pref: IEncryptedPreference<Boolean>): Boolean
    fun getLong(pref: IEncryptedPreference<Long>): Long

    fun putString(value: IEncryptedPreferenceValue<String>)
    fun putInt(value: IEncryptedPreferenceValue<Int>)
    fun putFloat(value: IEncryptedPreferenceValue<Float>)
    fun putDouble(value: IEncryptedPreferenceValue<Double>)
    fun putBoolean(value: IEncryptedPreferenceValue<Boolean>)
    fun putLong(value: IEncryptedPreferenceValue<Long>)
    fun putString(pref: IEncryptedPreference<String>, value: String)
    fun putInt(pref: IEncryptedPreference<Int>, value: Int)
    fun putFloat(pref: IEncryptedPreference<Float>, value: Float)
    fun putDouble(pref: IEncryptedPreference<Double>, value: Double)
    fun putBoolean(pref: IEncryptedPreference<Boolean>, value: Boolean)
    fun putLong(pref: IEncryptedPreference<Long>, value: Long)
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
