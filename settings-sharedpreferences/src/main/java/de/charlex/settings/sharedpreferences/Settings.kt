package de.charlex.settings.sharedpreferences

import android.content.Context

interface Settings {

    fun <T> get(pref: ISharedPreference<T>): T
    fun <T> put(pref: ISharedPreference<T>, value: T)

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

