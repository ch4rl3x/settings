package de.charlex.settings.core

class EncryptedPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedPreference<T>
