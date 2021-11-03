package de.charlex.settings.sharedpreferences

import kotlin.reflect.KProperty

interface ISharedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}

interface IEnumSharedPreference<T: Enum<T>, U> {
    val preferenceKey: String
    val defaultValue: T
    val keyProperty: KProperty<U>
}
