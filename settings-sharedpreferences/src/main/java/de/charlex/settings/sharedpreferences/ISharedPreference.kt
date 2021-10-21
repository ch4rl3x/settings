package de.charlex.settings.sharedpreferences


interface ISharedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}