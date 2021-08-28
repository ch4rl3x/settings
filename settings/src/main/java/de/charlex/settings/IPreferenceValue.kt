package de.charlex.settings

import org.jetbrains.annotations.NonNls

@NonNls
interface IPreferenceValue<T> {
    val value: T
    val preferenceKey: String
}
