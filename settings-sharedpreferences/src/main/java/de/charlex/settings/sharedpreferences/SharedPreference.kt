package de.charlex.settings.sharedpreferences

internal data class SharedPreference<T> (
    override val preferenceKey: String,
    override val defaultValue: T
) : ISharedPreference<T>

internal data class SharedEnumPreference<T> (
    override val preferenceKey: String,
    override val defaultValue: T,
) : ISharedEnumPreference<T> where T : Enum<T>, T : Keyed

fun stringPreference(name: String, defaultValue: String): ISharedPreference<String> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun <T> enumPreference(name: String, defaultValue: T): ISharedEnumPreference<T> where T : Enum<T>, T : Keyed =
    SharedEnumPreference(preferenceKey = name, defaultValue = defaultValue)

fun booleanPreference(name: String, defaultValue: Boolean): ISharedPreference<Boolean> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun intPreference(name: String, defaultValue: Int): ISharedPreference<Int> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun floatPreference(name: String, defaultValue: Float): ISharedPreference<Float> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun longPreference(name: String, defaultValue: Long): ISharedPreference<Long> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun doublePreference(name: String, defaultValue: Double): ISharedPreference<Double> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)

fun stringSetPreference(name: String, defaultValue: Set<String>): ISharedPreference<Set<String>> =
    SharedPreference(preferenceKey = name, defaultValue = defaultValue)
