package de.charlex.settings.datastore.encryption

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

internal data class EncryptedPreference<T> (
    override val defaultValue: T,
    override val preferenceKey: Preferences.Key<String>,
) : IDataStoreEncryptedPreference<T>

fun encryptedStringPreference(name: String, defaultValue: String): IDataStoreEncryptedPreference<String> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun <T : Enum<T>> encryptedEnumPreference(name: String, defaultValue: T): IDataStoreEncryptedPreference<T> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedBooleanPreference(name: String, defaultValue: Boolean): IDataStoreEncryptedPreference<Boolean> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedIntPreference(name: String, defaultValue: Int): IDataStoreEncryptedPreference<Int> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedFloatPreference(name: String, defaultValue: Float): IDataStoreEncryptedPreference<Float> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedLongPreference(name: String, defaultValue: Long): IDataStoreEncryptedPreference<Long> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedDoublePreference(name: String, defaultValue: Double): IDataStoreEncryptedPreference<Double> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

fun encryptedStringSetPreference(name: String, defaultValue: Set<String>): IDataStoreEncryptedPreference<Set<String>> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)
