package de.charlex.settings.core

open class PreferenceValue<T>(
    preferences: IPreference<T>,
    override val value: T
) : IPreferenceValue<T> {

    override val preferenceKey: String = preferences.preferenceKey
}
