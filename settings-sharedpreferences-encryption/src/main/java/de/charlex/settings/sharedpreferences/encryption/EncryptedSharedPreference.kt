package de.charlex.settings.sharedpreferences.encryption

import kotlin.reflect.KProperty

internal data class EncryptedSharedPreference<T>(
    override val preferenceKey: String,
    override val defaultValue: T
) : IEncryptedSharedPreference<T>

internal data class EncryptedSharedEnumPreference<T : Enum<T>, U>(
    override val preferenceKey: String,
    override val defaultValue: T,
    override val keyProperty: KProperty<U>
) : IEncryptedEnumSharedPreference<T, U>

fun encryptedStringPreference(name: String, defaultValue: String): IEncryptedSharedPreference<String> =
    EncryptedSharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun <T : Enum<T>, U> encryptedEnumPreference(name: String, defaultValue: T, keyProperty: KProperty<U>): IEncryptedEnumSharedPreference<T, U> =
    EncryptedSharedEnumPreference(preferenceKey = name, defaultValue = defaultValue, keyProperty = keyProperty)

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
