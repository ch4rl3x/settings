package de.charlex.settings.sharedpreferences

import android.content.Context
import de.charlex.settings.core.IPreference
import de.charlex.settings.core.Keyed

interface Settings {

    fun getRaw(key: String): String?
    fun getString(pref: IPreference<String>): String
    fun getInt(pref: IPreference<Int>): Int
    fun getFloat(pref: IPreference<Float>): Float
    fun getDouble(pref: IPreference<Double>): Double
    fun getBoolean(pref: IPreference<Boolean>): Boolean
    fun getLong(pref: IPreference<Long>): Long

    fun putString(pref: IPreference<String>, value: String)
    fun putInt(pref: IPreference<Int>, value: Int)
    fun putFloat(pref: IPreference<Float>, value: Float)
    fun putDouble(pref: IPreference<Double>, value: Double)
    fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    fun putLong(pref: IPreference<Long>, value: Long)
    fun <T> putEnum(pref: IPreference<T>, value: T) where T : Enum<T>, T : Keyed


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

inline fun <reified T> Settings.getEnum(pref: IPreference<T>): T where T : Enum<T>, T : Keyed {
    return enumValues<T>().find { it.key == getRaw(pref.preferenceKey) } ?: pref.defaultValue
}

