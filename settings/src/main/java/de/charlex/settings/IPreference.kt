package de.charlex.settings

interface IPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}