package de.charlex.settings.core

interface IPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}
