package de.charlex.settings.sharedpreferences

import de.charlex.settings.core.IPreference
import de.charlex.settings.core.IPreferenceValue

class SettingsInMemoryImpl internal constructor() : Settings {

    private val settings = mutableMapOf<String, Any>()

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

    override fun putString(value: IPreferenceValue<String>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putString(pref: IPreference<String>, value: String) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putInt(value: IPreferenceValue<Int>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putInt(pref: IPreference<Int>, value: Int) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putFloat(value: IPreferenceValue<Float>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putDouble(value: IPreferenceValue<Double>) {
        settings[value.preferenceKey] = java.lang.Double.doubleToRawLongBits(value.value) as Any
    }

    override fun putFloat(pref: IPreference<Float>, value: Float) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putDouble(pref: IPreference<Double>, value: Double) {
        settings[pref.preferenceKey] = java.lang.Double.doubleToRawLongBits(value) as Any
    }

    override fun putBoolean(value: IPreferenceValue<Boolean>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putLong(value: IPreferenceValue<Long>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putLong(pref: IPreference<Long>, value: Long) {
        settings[pref.preferenceKey] = value as Any
    }
}
