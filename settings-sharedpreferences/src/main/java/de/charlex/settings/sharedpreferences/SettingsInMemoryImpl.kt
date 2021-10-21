package de.charlex.settings.sharedpreferences


class SettingsInMemoryImpl internal constructor() : Settings {

    private val settings = mutableMapOf<String, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(pref: ISharedPreference<T>): T {
        return settings[pref.preferenceKey] as T ?: pref.defaultValue
    }

    override fun <T> put(pref: ISharedPreference<T>, value: T) {
        settings[pref.preferenceKey] = value as Any
    }
}
