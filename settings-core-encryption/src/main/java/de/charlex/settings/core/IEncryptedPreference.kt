package de.charlex.settings.core


interface IEncryptedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}
