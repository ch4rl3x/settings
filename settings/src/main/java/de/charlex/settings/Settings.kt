package de.charlex.settings

import android.content.SharedPreferences

interface Settings {
    fun getString(pref: IPreference<String>): String
    fun getInt(pref: IPreference<Int>): Int
    fun getFloat(pref: IPreference<Float>): Float
    fun getBoolean(pref: IPreference<Boolean>): Boolean
    fun getLong(pref: IPreference<Long>): Long
    fun putString(value: IPreferenceValue<String>)
    fun putInt(value: IPreferenceValue<Int>)
    fun putFloat(value: IPreferenceValue<Float>)
    fun putBoolean(value: IPreferenceValue<Boolean>)
    fun putLong(value: IPreferenceValue<Long>)
    fun putString(pref: IPreference<String>, value: String)
    fun putInt(pref: IPreference<Int>, value: Int)
    fun putFloat(pref: IPreference<Float>, value: Float)
    fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    fun putLong(pref: IPreference<Long>, value: Long)
    fun _getSharedPreferences(): SharedPreferences?
    fun _getSharedPreferencesEditor(): SharedPreferences.Editor?
}


