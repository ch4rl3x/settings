package de.charlex.settings.sharedpreferences.encryption

object EncryptedPreferences {
    val PreferenceInt = encryptedIntPreference("preference_int", 1)
    val PreferenceString = encryptedStringPreference("preference_string", "default")
    val PreferenceFloat = encryptedFloatPreference("preference_float", 1.1f)
    val PreferenceLong = encryptedLongPreference("preference_long", 1L)
    val PreferenceBoolean = encryptedBooleanPreference("preference_boolean", true)

    val PreferenceStringSet = encryptedStringSetPreference("preference_boolean", setOf("Element 1", "Element 2"))

    val PreferenceEnumStringKey = encryptedEnumPreference("key", StringKeyEnum.Value2, StringKeyEnum::key)
    val PreferenceEnumIntKey = encryptedEnumPreference("key", IntKeyEnum.Value2, IntKeyEnum::key)
    val PreferenceEnumOrdinalKey = encryptedEnumPreference("key", Enum.Value2, Enum::ordinal)
    val PreferenceEnumNameKey = encryptedEnumPreference("key", Enum.Value2, Enum::name)
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
