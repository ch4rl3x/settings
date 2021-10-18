package de.charlex.settings.core


interface IEncryptedPreferenceValue<T> {
    val value: T
    val preferenceKey: String
}
