package de.charlex.settings.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SettingsImpl internal constructor(
    context: Context,
    name: String,
    mode: Int
) : Settings {

    val settings: SharedPreferences = context.getSharedPreferences(name, mode)

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(pref: ISharedPreference<T>): T {
        return settings.all[pref.preferenceKey] as T ?: pref.defaultValue
    }

    override fun <T> put(pref: ISharedPreference<T>, value: T) {
        val edit = settings.edit()
        val name = pref.preferenceKey
        when (value) {
            is String -> edit.putString(name, value)
            is Boolean -> edit.putBoolean(name, value)
            is Int -> edit.putInt(name, value)
            is Long -> edit.putLong(name, value)
            is Float -> edit.putFloat(name, value)
            else -> error("Cannot save ${pref.preferenceKey}")
        }
        edit.commit()
    }


}

