package de.charlex.settings.sharedpreferences

interface ISharedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}

interface ISharedEnumPreference<T> where T : Enum<*>, T : Keyed {
    val preferenceKey: String
    val defaultValue: T
}
