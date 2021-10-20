package de.charlex.settings.core

interface IEncryptedEnumPreference<T>: IEncryptedPreference<T> where T: Enum<T>, T: Keyed
