package de.charlex.settings.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlin.reflect.KProperty

internal data class Preference<T> (
    override val preferenceKey: Preferences.Key<T>,
    override val defaultValue: T,
) : IDataStorePreference<T>

internal data class EnumPreference<T, U> (
    override val preferenceKey: Preferences.Key<U>,
    override val defaultValue: T,
    override val keyProperty: KProperty<U>,
) : IDataStoreEnumPreference<T, U>

fun stringPreference(name: String, defaultValue: String): IDataStorePreference<String> =
    Preference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

@Suppress("UNCHECKED_CAST")
fun <T : Enum<T>, U> enumPreference(name: String, defaultValue: T, keyProperty: KProperty<U>): IDataStoreEnumPreference<T, U> {
    val preferenceKey: Preferences.Key<U> = when(keyProperty.call(defaultValue)) {
        is String -> stringPreferencesKey(name) as Preferences.Key<U>
        is Int -> intPreferencesKey(name) as Preferences.Key<U>
        is Double -> doublePreferencesKey(name) as Preferences.Key<U>
        is Boolean -> booleanPreferencesKey(name) as Preferences.Key<U>
        is Float -> floatPreferencesKey(name) as Preferences.Key<U>
        is Long -> longPreferencesKey(name) as Preferences.Key<U>
        else -> error("Invalid type for enumPreferences")
    }
    return EnumPreference(preferenceKey = preferenceKey, defaultValue = defaultValue, keyProperty = keyProperty)
}

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
