package de.charlex.settings

class Preference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IPreference<T>
