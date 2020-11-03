package de.charlex.settings

interface IPreferenceValue<T> {
    val value: T
    val preferenceKey: String
}
