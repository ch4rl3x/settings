package de.charlex.settings.sharedpreferences


interface IPreference<T> {
    val preferenceKey: IPreferenceKey<T>
    val defaultValue: T
}