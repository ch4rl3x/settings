package de.charlex.settings.sharedpreferences

import de.charlex.settings.core.IPreference
import de.charlex.settings.core.Keyed

class SettingsInMemoryImpl internal constructor() : Settings {

    private val settings = mutableMapOf<String, Any>()

    override fun getRaw(key: String): String? {
        return settings[key] as? String
    }

    override fun getString(pref: IPreference<String>): String {
        return settings[pref.preferenceKey] as? String ?: pref.defaultValue
    }

    override fun getInt(pref: IPreference<Int>): Int {
        return settings[pref.preferenceKey] as? Int ?: pref.defaultValue
    }

    override fun getFloat(pref: IPreference<Float>): Float {
        return settings[pref.preferenceKey] as? Float ?: pref.defaultValue
    }

    override fun getDouble(pref: IPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings[pref.preferenceKey] as? Long ?: java.lang.Double.doubleToRawLongBits(pref.defaultValue))
    }

    override fun getBoolean(pref: IPreference<Boolean>): Boolean {
        return settings[pref.preferenceKey] as? Boolean ?: pref.defaultValue
    }

    override fun getLong(pref: IPreference<Long>): Long {
        return settings[pref.preferenceKey] as? Long ?: pref.defaultValue
    }

    override fun putString(pref: IPreference<String>, value: String) {
        settings[pref.preferenceKey] = value
    }

    override fun putInt(pref: IPreference<Int>, value: Int) {
        settings[pref.preferenceKey] = value
    }

    override fun putFloat(pref: IPreference<Float>, value: Float) {
        settings[pref.preferenceKey] = value
    }

    override fun putDouble(pref: IPreference<Double>, value: Double) {
        settings[pref.preferenceKey] = java.lang.Double.doubleToRawLongBits(value)
    }

    override fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        settings[pref.preferenceKey] = value
    }

    override fun putLong(pref: IPreference<Long>, value: Long) {
        settings[pref.preferenceKey] = value
    }

    override fun <T> putEnum(pref: IPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        settings[pref.preferenceKey] = value.key
    }
}
