package de.charlex.settings.sharedpreferences.encryption

class EncryptedSettingsInMemoryImpl internal constructor() : EncryptedSettings {

    private val settings = mutableMapOf<String, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(pref: IEncryptedSharedPreference<T>): T {
        return settings[pref.preferenceKey] as T ?: pref.defaultValue
    }

    override fun <T> put(pref: IEncryptedSharedPreference<T>, value: T) {
        if (value is Set<*> && value.filterIsInstance<String>().size != value.size) {
            error(
                "Cannot save preference with key: ${pref.preferenceKey}, value: ${value}\".\n" +
                    "Only String sets can be saved and this set seems to contain other instances."
            )
        }
        settings[pref.preferenceKey] = value as Any
    }

    override fun <T : Enum<T>, U> put(pref: IEncryptedEnumSharedPreference<T, U>, value: T) {
        settings[pref.preferenceKey] = pref.keyProperty.call(value) as Any
    }

    override fun <T> remove(pref: IEncryptedSharedPreference<T>) {
        settings.remove(pref.preferenceKey)
    }

    override fun <T : Enum<T>, U> remove(pref: IEncryptedEnumSharedPreference<T, U>) {
        settings.remove(pref.preferenceKey)
    }
}
