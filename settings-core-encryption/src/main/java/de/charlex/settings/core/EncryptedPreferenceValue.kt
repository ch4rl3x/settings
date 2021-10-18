package de.charlex.settings.core

class EncryptedPreferenceValue<T>(
    preferences: IEncryptedPreference<T>,
    override val value: T
) : IEncryptedPreferenceValue<T> {

    override val preferenceKey: String = preferences.preferenceKey
}
