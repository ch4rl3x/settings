package de.charlex.settings.sharedpreferences.encryption

class EncryptedPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedPreference<T>
