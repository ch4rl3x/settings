package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

class EncryptedSettingsInMemoryImpl internal constructor() : EncryptedSettings {

    private val settings = mutableMapOf<String, Any>()

    override fun getRaw(key: String): String? {
        return settings[key] as? String
    }

    override fun getString(pref: IEncryptedSharedPreference<String>): String {
        return settings[pref.preferenceKey] as? String ?: pref.defaultValue
    }

    override fun getInt(pref: IEncryptedSharedPreference<Int>): Int {
        return settings[pref.preferenceKey] as? Int ?: pref.defaultValue
    }

    override fun getFloat(pref: IEncryptedSharedPreference<Float>): Float {
        return settings[pref.preferenceKey] as? Float ?: pref.defaultValue
    }

    override fun getDouble(pref: IEncryptedSharedPreference<Double>): Double {
        return java.lang.Double.longBitsToDouble(settings[pref.preferenceKey] as? Long ?: java.lang.Double.doubleToRawLongBits(pref.defaultValue))
    }

    override fun getBoolean(pref: IEncryptedSharedPreference<Boolean>): Boolean {
        return settings[pref.preferenceKey] as? Boolean ?: pref.defaultValue
    }

    override fun getLong(pref: IEncryptedSharedPreference<Long>): Long {
        return settings[pref.preferenceKey] as? Long ?: pref.defaultValue
    }

    override fun getStringSet(pref: IEncryptedSharedPreference<Set<String>>): Set<String> {
        return settings[pref.preferenceKey] as? Set<String> ?: pref.defaultValue
    }

    override fun putString(pref: IEncryptedSharedPreference<String>, value: String) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putInt(pref: IEncryptedSharedPreference<Int>, value: Int) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putFloat(pref: IEncryptedSharedPreference<Float>, value: Float) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putDouble(pref: IEncryptedSharedPreference<Double>, value: Double) {
        settings[pref.preferenceKey] = java.lang.Double.doubleToRawLongBits(value) as Any
    }

    override fun putBoolean(pref: IEncryptedSharedPreference<Boolean>, value: Boolean) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun putLong(pref: IEncryptedSharedPreference<Long>, value: Long) {
        settings[pref.preferenceKey] = value as Any
    }

    override fun <T> putEnum(pref: IEncryptedSharedPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        settings[pref.preferenceKey] = value.key
    }

    override fun putStringSet(pref: IEncryptedSharedPreference<Set<String>>, value: Set<String>) {
        settings[pref.preferenceKey] = value as Any
    }
}
