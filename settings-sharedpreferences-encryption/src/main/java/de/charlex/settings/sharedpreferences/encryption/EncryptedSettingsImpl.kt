package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import de.charlex.settings.sharedpreferences.Keyed

class EncryptedSettingsImpl internal constructor(
    context: Context,
    name: String,
    mainKeyAlias: String,
    prefKeyEncryptionScheme: EncryptedSharedPreferences.PrefKeyEncryptionScheme,
    prefValueEncryptionScheme: EncryptedSharedPreferences.PrefValueEncryptionScheme
) : EncryptedSettings {

    val settings: SharedPreferences = EncryptedSharedPreferences.create(
        name,
        mainKeyAlias,
        context,
        prefKeyEncryptionScheme,
        prefValueEncryptionScheme
    )

    override fun getRaw(key: String): String? {
        return settings.getString(key, null)
    }

    override fun getString(pref: IEncryptedSharedPreference<String>): String {
        return settings.getString(pref.preferenceKey, pref.defaultValue) ?: ""
    }

    override fun getInt(pref: IEncryptedSharedPreference<Int>): Int {
        return settings.getInt(pref.preferenceKey, pref.defaultValue)
    }

    override fun getFloat(pref: IEncryptedSharedPreference<Float>): Float {
        return settings.getFloat(pref.preferenceKey, pref.defaultValue)
    }

    override fun getDouble(pref: IEncryptedSharedPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings.getLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(pref.defaultValue)))
    }

    override fun getBoolean(pref: IEncryptedSharedPreference<Boolean>): Boolean {
        return settings.getBoolean(pref.preferenceKey, pref.defaultValue)
    }

    override fun getLong(pref: IEncryptedSharedPreference<Long>): Long {
        return settings.getLong(pref.preferenceKey, pref.defaultValue)
    }

    override fun getStringSet(pref: IEncryptedSharedPreference<Set<String>>): Set<String> {
        return settings.getStringSet(pref.preferenceKey, pref.defaultValue) ?: setOf()
    }

    override fun putString(pref: IEncryptedSharedPreference<String>, value: String) {
        settings.edit().putString(pref.preferenceKey, value).apply()
    }

    override fun putInt(pref: IEncryptedSharedPreference<Int>, value: Int) {
        settings.edit().putInt(pref.preferenceKey, value).apply()
    }

    override fun putFloat(pref: IEncryptedSharedPreference<Float>, value: Float) {
        settings.edit().putFloat(pref.preferenceKey, value).apply()
    }

    override fun putDouble(pref: IEncryptedSharedPreference<Double>, value: Double) {
        settings.edit().putLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    override fun putBoolean(pref: IEncryptedSharedPreference<Boolean>, value: Boolean) {
        settings.edit().putBoolean(pref.preferenceKey, value).apply()
    }

    override fun putLong(pref: IEncryptedSharedPreference<Long>, value: Long) {
        settings.edit().putLong(pref.preferenceKey, value).apply()
    }

    override fun <T> putEnum(pref: IEncryptedSharedPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        settings.edit().putString(pref.preferenceKey, value.key).apply()
    }

    override fun putStringSet(pref: IEncryptedSharedPreference<Set<String>>, value: Set<String>) {
        settings.edit().putStringSet(pref.preferenceKey, value).apply()
    }
}
