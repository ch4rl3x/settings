package de.charlex.settings.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

data class Preference<T> (
    override val preferenceKey: Preferences.Key<T>,
    override val defaultValue: T,
) : IDataStorePreference<T>

data class EnumPreference<T, U> (
    override val preferenceKey: Preferences.Key<U>,
    override val defaultValue: T,
    override val keyProperty: KCallable<U>,
) : IDataStoreEnumPreference<T, U>

@Suppress("UNCHECKED_CAST")
inline fun <reified T> preference(name: String, defaultValue: T): IDataStorePreference<T> {
    val key: Preferences.Key<T> = when (T::class) {
        String::class -> stringPreferencesKey(name) as Preferences.Key<T>
        Int::class -> intPreferencesKey(name) as Preferences.Key<T>
        Double::class -> doublePreferencesKey(name) as Preferences.Key<T>
        Boolean::class -> booleanPreferencesKey(name) as Preferences.Key<T>
        Float::class -> floatPreferencesKey(name) as Preferences.Key<T>
        Long::class -> longPreferencesKey(name) as Preferences.Key<T>
        else -> error("Invalid type for preference: ${T::class}")
    }
    return Preference(preferenceKey = key, defaultValue = defaultValue)
}

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun stringPreference(name: String, defaultValue: String): IDataStorePreference<String> =
    preference(name, defaultValue)

@Suppress("UNCHECKED_CAST")
fun <T : Enum<T>, U> enumPreference(name: String, defaultValue: T, keyProperty: KProperty<U>): IDataStoreEnumPreference<T, U> {
    val preferenceKey: Preferences.Key<U> = when (keyProperty.call(defaultValue)) {
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

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun booleanPreference(name: String, defaultValue: Boolean): IDataStorePreference<Boolean> =
    preference(name, defaultValue)

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun intPreference(name: String, defaultValue: Int): IDataStorePreference<Int> =
    preference(name, defaultValue)

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun floatPreference(name: String, defaultValue: Float): IDataStorePreference<Float> =
    preference(name, defaultValue)

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun longPreference(name: String, defaultValue: Long): IDataStorePreference<Long> =
    preference(name, defaultValue)

@Deprecated(
    "Use preference() instead",
    ReplaceWith("preference(name, defaultValue)")
)
fun doublePreference(name: String, defaultValue: Double): IDataStorePreference<Double> =
    preference(name, defaultValue)

@JvmName("stringSetPreference")
fun stringSetPreference(name: String, defaultValue: Set<String>): IDataStorePreference<Set<String>> =
    Preference(preferenceKey = stringSetPreferencesKey(name), defaultValue = defaultValue)

@JvmName("nullableStringSetPreference")
fun stringSetPreference(name: String, defaultValue: Set<String>?): IDataStorePreference<Set<String>?> =
    Preference(preferenceKey = stringSetPreferencesKey(name) as Preferences.Key<Set<String>?>, defaultValue = defaultValue)
