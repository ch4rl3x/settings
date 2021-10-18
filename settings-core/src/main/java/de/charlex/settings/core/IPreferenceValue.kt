package de.charlex.settings.core

interface IPreferenceValue<T> {
    val value: T
    val preferenceKey: String
}
