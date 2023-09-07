package de.charlex.settings.datastore

object Preferences {

    val PreferenceInt = intPreference("preference_int", 1)
    val PreferenceString = stringPreference("preference_string", "default")

    val PreferenceString_WithIntKey = stringPreference("preference_int", "default")
    val PreferenceFloat = floatPreference("preference_float", 1.1f)
    val PreferenceDouble = doublePreference("preference_double", 1.1)
    val PreferenceLong = longPreference("preference_long", 1L)
    val PreferenceBoolean = booleanPreference("preference_boolean", true)
    val PreferenceStringSet = stringSetPreference("preference_stringset", setOf())

    val PreferenceIntNullable = intPreference("preference_int_nullable", null)
    val PreferenceStringNullable = stringPreference("preference_string_nullable", null)
    val PreferenceFloatNullable = floatPreference("preference_float_nullable", null)
    val PreferenceDoubleNullable = doublePreference("preference_double_nullable", null)
    val PreferenceLongNullable = longPreference("preference_long_nullable", null)
    val PreferenceBooleanNullable = booleanPreference("preference_boolean_nullable", null)
    val PreferenceStringSetNullable = stringSetPreference("preference_stringset_nullable", null)

    val PreferenceEnumStringKey = enumPreference("key", StringKeyEnum.Value2, StringKeyEnum::key)
    val PreferenceEnumIntKey = enumPreference("key", IntKeyEnum.Value2, IntKeyEnum::key)
    val PreferenceEnumOrdinalKey = enumPreference("key", TestEnum.Value2, TestEnum::ordinal)
    val PreferenceEnumNameKey = enumPreference("key", TestEnum.Value2, TestEnum::name)
}

enum class StringKeyEnum(val key: String) {
    Value1("value1"),
    Value2("value2"),
    Value3("value3")
}

enum class IntKeyEnum(val key: Int) {
    Value1(0),
    Value2(1),
    Value3(2)
}

enum class TestEnum {
    Value1,
    Value2,
    Value3
}
