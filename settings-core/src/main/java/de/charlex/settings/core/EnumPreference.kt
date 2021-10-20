package de.charlex.settings.core

open class EnumPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEnumPreference<T>  where T: Enum<T>, T: Keyed

