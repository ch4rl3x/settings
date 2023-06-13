package de.charlex.settings.datastore

object Preferences {

    val PreferenceInt = preference("preference_int", 1)
    val PreferenceString = preference("preference_string", "default")

    val PreferenceString_WithIntKey = preference("preference_int", "default")
    val PreferenceFloat = preference("preference_float", 1.1f)
    val PreferenceDouble = preference("preference_double", 1.1)
    val PreferenceLong = preference("preference_long", 1L)
    val PreferenceBoolean = preference("preference_boolean", true)
    val PreferenceStringSet = stringSetPreference("preference_stringset", setOf())

    val PreferenceIntNullable = preference<Int?>("preference_int_nullable", null)
    val PreferenceStringNullable = preference<String?>("preference_string_nullable", null)
    val PreferenceFloatNullable = preference<Float?>("preference_float_nullable", null)
    val PreferenceDoubleNullable = preference<Double?>("preference_double_nullable", null)
    val PreferenceLongNullable = preference<Long?>("preference_long_nullable", null)
    val PreferenceBooleanNullable = preference<Boolean?>("preference_boolean_nullable", null)
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
