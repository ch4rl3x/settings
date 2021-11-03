package de.charlex.settings.sharedpreferences

import android.content.Context

interface Settings {

    fun <T> get(pref: ISharedPreference<T>): T
    fun <T> put(pref: ISharedPreference<T>, value: T)
    fun <T: Enum<T>, U> put(pref: IEnumSharedPreference<T, U>, value: T)

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

inline fun <reified T : Enum<T>, U> Settings.get(pref: IEnumSharedPreference<T, U>): T {
    val keyProperty = pref.keyProperty
    val sharedPreference = when(val defaultValue = keyProperty.call(pref.defaultValue)) {
        is String -> stringPreference(pref.preferenceKey, defaultValue as String)
        is Int -> intPreference(pref.preferenceKey, defaultValue as Int)
        is Float -> floatPreference(pref.preferenceKey, defaultValue as Float)
        is Long -> longPreference(pref.preferenceKey, defaultValue as Long)
        is Boolean -> booleanPreference(pref.preferenceKey, defaultValue as Boolean)
        else -> error("No valid enum key: $defaultValue")
    }
    val enumKey = get(sharedPreference)
    return enumValues<T>().find { pref.keyProperty.call(it) == enumKey } ?: pref.defaultValue
}
