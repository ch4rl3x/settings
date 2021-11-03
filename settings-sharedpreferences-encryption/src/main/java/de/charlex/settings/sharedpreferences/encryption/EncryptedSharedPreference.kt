package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

internal data class EncryptedSharedPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedSharedPreference<T>

internal data class EncryptedSharedEnumPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedEnumSharedPreference<T> where T : Enum<T>, T : Keyed

fun encryptedStringPreference(name: String, defaultValue: String): IEncryptedSharedPreference<String> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun <T> encryptedEnumPreference(name: String, defaultValue: T): IEncryptedEnumSharedPreference<T> where T : Enum<T>, T : Keyed =
    EncryptedSharedEnumPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedBooleanPreference(name: String, defaultValue: Boolean): IEncryptedSharedPreference<Boolean> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedIntPreference(name: String, defaultValue: Int): IEncryptedSharedPreference<Int> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedFloatPreference(name: String, defaultValue: Float): IEncryptedSharedPreference<Float> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedLongPreference(name: String, defaultValue: Long): IEncryptedSharedPreference<Long> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedDoublePreference(name: String, defaultValue: Double): IEncryptedSharedPreference<Double> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun encryptedStringSetPreference(name: String, defaultValue: Set<String>): IEncryptedSharedPreference<Set<String>> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)
