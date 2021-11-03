package de.charlex.settings.sharedpreferences

object Preferences {

    val PreferenceInt = intPreference("preference_int", 1)
    val PreferenceString = stringPreference("preference_string", "default")
    val PreferenceFloat = floatPreference("preference_float", 1.1f)
    val PreferenceLong = longPreference("preference_long", 1L)
    val PreferenceBoolean = booleanPreference("preference_boolean", true)

    val PreferenceStringSet = stringSetPreference("preference_stringset", setOf())

    val PreferenceEnumStringKey = enumPreference("key", StringKeyEnum.Value2, StringKeyEnum::key)
    val PreferenceEnumIntKey = enumPreference("key", IntKeyEnum.Value2, IntKeyEnum::key)
    val PreferenceEnumOrdinalKey = enumPreference("key", Enum.Value2, Enum::ordinal)
    val PreferenceEnumNameKey = enumPreference("key", Enum.Value2, Enum::name)
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

enum class Enum {
    Value1,
    Value2,
    Value3
}

