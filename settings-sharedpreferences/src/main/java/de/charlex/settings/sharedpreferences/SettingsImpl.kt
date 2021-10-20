package de.charlex.settings.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import de.charlex.settings.core.*

class SettingsImpl internal constructor(
    context: Context,
    name: String,
    mode: Int
) : Settings {

    val settings: SharedPreferences = context.getSharedPreferences(name, mode)

    override fun getRaw(key: String): String? {
        return settings.getString(key, null)
    }

    override fun getString(pref: IPreference<String>): String {
        return settings.getString(pref.preferenceKey, pref.defaultValue) ?: ""
    }

    override fun getInt(pref: IPreference<Int>): Int {
        return settings.getInt(pref.preferenceKey, pref.defaultValue)
    }

    override fun getFloat(pref: IPreference<Float>): Float {
        return settings.getFloat(pref.preferenceKey, pref.defaultValue)
    }

    override fun getDouble(pref: IPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings.getLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(pref.defaultValue)))
    }

    override fun getBoolean(pref: IPreference<Boolean>): Boolean {
        return settings.getBoolean(pref.preferenceKey, pref.defaultValue)
    }

    override fun getLong(pref: IPreference<Long>): Long {
        return settings.getLong(pref.preferenceKey, pref.defaultValue)
    }

    override fun putString(pref: IPreference<String>, value: String) {
        settings.edit().putString(pref.preferenceKey, value).apply()
    }

    override fun putInt(pref: IPreference<Int>, value: Int) {
        settings.edit().putInt(pref.preferenceKey, value).apply()
    }

    override fun putFloat(pref: IPreference<Float>, value: Float) {
        settings.edit().putFloat(pref.preferenceKey, value).apply()
    }

    override fun putDouble(pref: IPreference<Double>, value: Double) {
        settings.edit().putLong(pref.preferenceKey, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    override fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        settings.edit().putBoolean(pref.preferenceKey, value).apply()
    }

    override fun putLong(pref: IPreference<Long>, value: Long) {
        settings.edit().putLong(pref.preferenceKey, value).apply()
    }

    override fun <T> putEnum(pref: IPreference<T>, value:T) where T : Enum<T>, T : Keyed {
        settings.edit().putString(pref.preferenceKey, value.key).apply()
    }


}

