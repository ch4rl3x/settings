package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed


class EncryptedSettingsInMemoryImpl internal constructor() : EncryptedSettings {

    private val settings = mutableMapOf<String, Any>()

    override fun getRaw(key: String): String? {
        return settings[key] as? String
    }

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

    override fun putString(pref: IEncryptedPreference<String>, value: String) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putInt(pref: IEncryptedPreference<Int>, value: Int) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putFloat(pref: IEncryptedPreference<Float>, value: Float) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putDouble(pref: IEncryptedPreference<Double>, value: Double) {
        settings[pref.preferenceKey] = java.lang.Double.doubleToRawLongBits(value) as Any
    }

    override fun putBoolean(pref: IEncryptedPreference<Boolean>, value: Boolean) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putLong(pref: IEncryptedPreference<Long>, value: Long) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun <T> putEnum(pref: IEncryptedPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        settings[pref.preferenceKey] = value.key
    }
}
