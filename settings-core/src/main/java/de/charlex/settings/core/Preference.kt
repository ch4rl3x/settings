package de.charlex.settings.core

open class Preference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IPreference<T>
