package de.charlex.settings.core

class EncryptedEnumPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedEnumPreference<T>
