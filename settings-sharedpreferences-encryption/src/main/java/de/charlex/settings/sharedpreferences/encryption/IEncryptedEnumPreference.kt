package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

interface IEncryptedEnumPreference<T> : IEncryptedPreference<T> where T : Enum<T>, T : Keyed
