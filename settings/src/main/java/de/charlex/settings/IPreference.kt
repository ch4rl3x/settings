package de.charlex.settings

import org.jetbrains.annotations.NonNls

@NonNls
interface IPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}
