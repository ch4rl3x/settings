package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

class EncryptedEnumPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedEnumPreference<T> where T : Enum<T>, T : Keyed
