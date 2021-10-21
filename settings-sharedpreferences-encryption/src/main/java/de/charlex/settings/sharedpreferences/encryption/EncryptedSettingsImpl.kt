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

    override fun getString(pref: IEncryptedPreference<String>): String {
        return settings.getString(pref.preferenceKey, pref.defaultValue) ?: ""
    }

    override fun getInt(pref: IEncryptedPreference<Int>): Int {
        return settings.getInt(pref.preferenceKey, pref.defaultValue)
    }

    override fun getFloat(pref: IEncryptedPreference<Float>): Float {
        return settings.getFloat(pref.preferenceKey, pref.defaultValue)
    }

    override fun getDouble(pref: IEncryptedPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings.getLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(pref.defaultValue)))
    }

    override fun getBoolean(pref: IEncryptedPreference<Boolean>): Boolean {
        return settings.getBoolean(pref.preferenceKey, pref.defaultValue)
    }

    override fun getLong(pref: IEncryptedPreference<Long>): Long {
        return settings.getLong(pref.preferenceKey, pref.defaultValue)
    }

    override fun putString(pref: IEncryptedPreference<String>, value: String) {
        settings.edit().putString(pref.preferenceKey, value).apply()
    }

    override fun putInt(pref: IEncryptedPreference<Int>, value: Int) {
        settings.edit().putInt(pref.preferenceKey, value).apply()
    }

    override fun putFloat(pref: IEncryptedPreference<Float>, value: Float) {
        settings.edit().putFloat(pref.preferenceKey, value).apply()
    }

    override fun putDouble(pref: IEncryptedPreference<Double>, value: Double) {
        settings.edit().putLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    override fun putBoolean(pref: IEncryptedPreference<Boolean>, value: Boolean) {
        settings.edit().putBoolean(pref.preferenceKey, value).apply()
    }

    override fun putLong(pref: IEncryptedPreference<Long>, value: Long) {
        settings.edit().putLong(pref.preferenceKey, value).apply()
    }

    override fun <T> putEnum(pref: IEncryptedPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        settings.edit().putString(pref.preferenceKey, value.key).apply()
    }
}
