package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

interface IEncryptedSharedPreference<T> {
    val preferenceKey: String
    val defaultValue: T
}

interface IEncryptedEnumSharedPreference<T> : IEncryptedSharedPreference<T> where T : Enum<T>, T : Keyed
