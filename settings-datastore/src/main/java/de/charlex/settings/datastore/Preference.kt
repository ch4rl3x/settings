package de.charlex.settings.datastore

import androidx.datastore.preferences.core.*

internal data class Preference<T> (
    override val preferenceKey: Preferences.Key<T>,
    override val defaultValue: T,
) : IDataStorePreference<T>

internal data class EnumPreference<T> (
    override val preferenceKey: Preferences.Key<String>,
    override val defaultValue: T,
) : IDataStoreEnumPreference<T>

fun stringPreference(name: String, defaultValue: String): IDataStorePreference<String> =
    Preference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun <T> enumPreference(name: String, defaultValue: T): IDataStoreEnumPreference<T> where T : Enum<T>, T : Keyed =
    EnumPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun booleanPreference(name: String, defaultValue: Boolean): IDataStorePreference<Boolean> =
    Preference(preferenceKey = booleanPreferencesKey(name), defaultValue = defaultValue)

fun intPreference(name: String, defaultValue: Int): IDataStorePreference<Int> =
    Preference(preferenceKey = intPreferencesKey(name), defaultValue = defaultValue)

fun floatPreference(name: String, defaultValue: Float): IDataStorePreference<Float> =
    Preference(preferenceKey = floatPreferencesKey(name), defaultValue = defaultValue)

fun longPreference(name: String, defaultValue: Long): IDataStorePreference<Long> =
    Preference(preferenceKey = longPreferencesKey(name), defaultValue = defaultValue)

fun doublePreference(name: String, defaultValue: Double): IDataStorePreference<Double> =
    Preference(preferenceKey = doublePreferencesKey(name), defaultValue = defaultValue)

fun stringSetPreference(name: String, defaultValue: Set<String>): IDataStorePreference<Set<String>> =
    Preference(preferenceKey = stringSetPreferencesKey(name), defaultValue = defaultValue)
