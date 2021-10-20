package de.charlex.settings.core

interface IEnumPreference<T>: IPreference<T> where T: Enum<T>, T: Keyed
