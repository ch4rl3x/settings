package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.core.IEncryptedPreference
import de.charlex.settings.core.IEncryptedPreferenceValue

class EncryptedSettingsInMemoryImpl internal constructor() : EncryptedSettings {

    private val settings = mutableMapOf<String, Any>()

    override fun getString(pref: IEncryptedPreference<String>): String {
        return settings[pref.preferenceKey] as? String ?: pref.defaultValue
    }

    override fun getInt(pref: IEncryptedPreference<Int>): Int {
        return settings[pref.preferenceKey] as? Int ?: pref.defaultValue
    }

    override fun getFloat(pref: IEncryptedPreference<Float>): Float {
        return settings[pref.preferenceKey] as? Float ?: pref.defaultValue
    }

    override fun getDouble(pref: IEncryptedPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings[pref.preferenceKey] as? Long ?: java.lang.Double.doubleToRawLongBits(pref.defaultValue))
    }

    override fun getBoolean(pref: IEncryptedPreference<Boolean>): Boolean {
        return settings[pref.preferenceKey] as? Boolean ?: pref.defaultValue
    }

    override fun getLong(pref: IEncryptedPreference<Long>): Long {
        return settings[pref.preferenceKey] as? Long ?: pref.defaultValue
    }

    override fun putString(value: IEncryptedPreferenceValue<String>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putString(pref: IEncryptedPreference<String>, value: String) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putInt(value: IEncryptedPreferenceValue<Int>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putInt(pref: IEncryptedPreference<Int>, value: Int) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putFloat(value: IEncryptedPreferenceValue<Float>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putDouble(value: IEncryptedPreferenceValue<Double>) {
        settings[value.preferenceKey] = java.lang.Double.doubleToRawLongBits(value.value) as Any
    }

    override fun putFloat(pref: IEncryptedPreference<Float>, value: Float) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putDouble(pref: IEncryptedPreference<Double>, value: Double) {
        settings[pref.preferenceKey] = java.lang.Double.doubleToRawLongBits(value) as Any
    }

    override fun putBoolean(value: IEncryptedPreferenceValue<Boolean>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putBoolean(pref: IEncryptedPreference<Boolean>, value: Boolean) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putLong(value: IEncryptedPreferenceValue<Long>) {
        settings[value.preferenceKey] = value.value as Any
    }

    override fun putLong(pref: IEncryptedPreference<Long>, value: Long) {
        settings[pref.preferenceKey] = value as Any
    }
}
