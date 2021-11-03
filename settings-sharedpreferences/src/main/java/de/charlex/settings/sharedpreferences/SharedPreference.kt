package de.charlex.settings.sharedpreferences

import kotlin.reflect.KProperty

internal data class SharedPreference<T> (
    override val preferenceKey: String,
    override val defaultValue: T
) : ISharedPreference<T>

internal data class EnumSharedPreference<T : Enum<T>, U> (
    override val preferenceKey: String,
    override val defaultValue: T,
    override val keyProperty: KProperty<U>,
) : IEnumSharedPreference<T, U>

fun stringPreference(name: String, defaultValue: String): ISharedPreference<String> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun <T : Enum<T>, U> enumPreference(name: String, defaultValue: T, keyProperty: KProperty<U>): IEnumSharedPreference<T, U> =
    EnumSharedPreference(preferenceKey = name, defaultValue = defaultValue, keyProperty = keyProperty)

fun booleanPreference(name: String, defaultValue: Boolean): ISharedPreference<Boolean> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun intPreference(name: String, defaultValue: Int): ISharedPreference<Int> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun floatPreference(name: String, defaultValue: Float): ISharedPreference<Float> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun longPreference(name: String, defaultValue: Long): ISharedPreference<Long> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun stringSetPreference(name: String, defaultValue: Set<String>): ISharedPreference<Set<String>> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)
