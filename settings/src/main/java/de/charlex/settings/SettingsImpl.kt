package de.charlex.settings

import android.content.Context
import android.content.SharedPreferences

internal class SettingsImpl(
    context: Context
) : Settings {

    private val settings: SharedPreferences = context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)

    override fun getString(pref: IPreference<String>): String {
        return settings.getString(pref.preferenceKey, pref.defaultValue) ?: ""
    }

    override fun getInt(pref: IPreference<Int>): Int {
        return settings.getInt(pref.preferenceKey, pref.defaultValue)
    }

    override fun getFloat(pref: IPreference<Float>): Float {
        return settings.getFloat(pref.preferenceKey, pref.defaultValue)
    }

    override fun getBoolean(pref: IPreference<Boolean>): Boolean {
        return settings.getBoolean(pref.preferenceKey, pref.defaultValue)
    }

    override fun getLong(pref: IPreference<Long>): Long {
        return settings.getLong(pref.preferenceKey, pref.defaultValue)
    }

    override fun putString(value: IPreferenceValue<String>) {
        settings.edit().putString(value.preferenceKey, value.value).apply()
    }

    override fun putInt(value: IPreferenceValue<Int>) {
        settings.edit().putInt(value.preferenceKey, value.value).apply()
    }

    override fun putFloat(value: IPreferenceValue<Float>) {
        settings.edit().putFloat(value.preferenceKey, value.value).apply()
    }

    override fun putBoolean(value: IPreferenceValue<Boolean>) {
        settings.edit().putBoolean(value.preferenceKey, value.value).apply()
    }

    override fun putLong(value: IPreferenceValue<Long>) {
        settings.edit().putLong(value.preferenceKey, value.value).apply()
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

    override fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        settings.edit().putBoolean(pref.preferenceKey, value).apply()
    }

    override fun putLong(pref: IPreference<Long>, value: Long) {
        settings.edit().putLong(pref.preferenceKey, value).apply()
    }

    override fun _getSharedPreferences(): SharedPreferences? {
        return settings
    }

    override fun _getSharedPreferencesEditor(): SharedPreferences.Editor? {
        return settings.edit()
    }
}
