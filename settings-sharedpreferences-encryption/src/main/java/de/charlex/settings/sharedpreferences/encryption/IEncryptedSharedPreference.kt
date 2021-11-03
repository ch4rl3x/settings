package de.charlex.settings.sharedpreferences.encryption

import kotlin.reflect.KProperty

interface IEncryptedSharedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}

interface IEncryptedEnumSharedPreference<T : Enum<T>, U> {
    val preferenceKey: String
    val defaultValue: T
    val keyProperty: KProperty<U>
}
