package de.charlex.settings.sharedpreferences.encryption

interface IEncryptedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}
