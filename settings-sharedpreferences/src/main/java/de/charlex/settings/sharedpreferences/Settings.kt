package de.charlex.settings.sharedpreferences

import android.content.Context
import de.charlex.settings.core.IPreference
import de.charlex.settings.core.IPreferenceValue

interface Settings {

    fun getString(pref: IPreference<String>): String
    fun getInt(pref: IPreference<Int>): Int
    fun getFloat(pref: IPreference<Float>): Float
    fun getDouble(pref: IPreference<Double>): Double
    fun getBoolean(pref: IPreference<Boolean>): Boolean
    fun getLong(pref: IPreference<Long>): Long

    fun putString(value: IPreferenceValue<String>)
    fun putInt(value: IPreferenceValue<Int>)
    fun putFloat(value: IPreferenceValue<Float>)
    fun putDouble(value: IPreferenceValue<Double>)
    fun putBoolean(value: IPreferenceValue<Boolean>)
    fun putLong(value: IPreferenceValue<Long>)
    fun putString(pref: IPreference<String>, value: String)
    fun putInt(pref: IPreference<Int>, value: Int)
    fun putFloat(pref: IPreference<Float>, value: Float)
    fun putDouble(pref: IPreference<Double>, value: Double)
    fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    fun putLong(pref: IPreference<Long>, value: Long)

    companion object {

        @JvmOverloads
        fun create(
            context: Context,
            name: String = context.packageName + "_preferences",
            mode: Int = Context.MODE_PRIVATE
        ): Settings {
            return SettingsImpl(
                context = context,
                name = name,
                mode = mode
            )
        }

        fun createInMemory(): Settings {
            return SettingsInMemoryImpl()
        }
    }
}
