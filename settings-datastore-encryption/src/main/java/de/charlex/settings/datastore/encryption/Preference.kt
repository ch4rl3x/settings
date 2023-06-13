package de.charlex.settings.datastore.encryption

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

data class EncryptedPreference<T> (
    override val defaultValue: T,
    override val preferenceKey: Preferences.Key<String>,
) : IDataStoreEncryptedPreference<T>

inline fun <reified T> encryptedPreference(name: String, defaultValue: T): IDataStoreEncryptedPreference<T> {
    val key = when (T::class) {
        String::class,
        Int::class,
        Double::class,
        Boolean::class,
        Float::class,
        Long::class -> stringPreferencesKey(name)
        else -> error("Invalid type for encryptedPreference: ${T::class}")
    }
    return EncryptedPreference(preferenceKey = key, defaultValue = defaultValue)
}

fun <T : Enum<T>> encryptedEnumPreference(name: String, defaultValue: T): IDataStoreEncryptedPreference<T> =
    EncryptedPreference(preferenceKey = stringPreferencesKey(name), defaultValue = defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedStringPreference(name: String, defaultValue: String): IDataStoreEncryptedPreference<String> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedBooleanPreference(name: String, defaultValue: Boolean): IDataStoreEncryptedPreference<Boolean> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedIntPreference(name: String, defaultValue: Int): IDataStoreEncryptedPreference<Int> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedFloatPreference(name: String, defaultValue: Float): IDataStoreEncryptedPreference<Float> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedLongPreference(name: String, defaultValue: Long): IDataStoreEncryptedPreference<Long> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedDoublePreference(name: String, defaultValue: Double): IDataStoreEncryptedPreference<Double> =
    encryptedPreference(name, defaultValue)

@Deprecated(
    "Use encryptedPreference() instead",
    ReplaceWith("encryptedPreference(name, defaultValue)")
)
fun encryptedStringSetPreference(name: String, defaultValue: Set<String>): IDataStoreEncryptedPreference<Set<String>> =
    encryptedPreference(name, defaultValue)
