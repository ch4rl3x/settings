package de.charlex.settings.datastore.encryption

object EncryptedPreferences {

    val PreferenceInt = encryptedPreference("preference_int", 1)
    val PreferenceString = encryptedPreference("preference_string", "default")
    val PreferenceString2 = encryptedPreference("preference_string_2", "default2")
    val PreferenceString3 = encryptedPreference("preference_string_3", "default3")
    val PreferenceString_WithIntKey = encryptedPreference("preference_int", "default")
    val PreferenceFloat = encryptedPreference("preference_float", 1.1f)
    val PreferenceDouble = encryptedPreference("preference_double", 1.1)
    val PreferenceLong = encryptedPreference("preference_long", 1L)
    val PreferenceBoolean = encryptedPreference("preference_boolean", true)

    val PreferenceIntNullable = encryptedPreference<Int?>("preference_int_nullable", null)
    val PreferenceStringNullable = encryptedPreference<String?>("preference_string_nullable", null)
    val PreferenceFloatNullable = encryptedPreference<Float?>("preference_float_nullable", null)
    val PreferenceDoubleNullable = encryptedPreference<Double?>("preference_double_nullable", null)
    val PreferenceLongNullable = encryptedPreference<Long?>("preference_long_nullable", null)
    val PreferenceBooleanNullable = encryptedPreference<Boolean?>("preference_boolean_nullable", null)

    val PreferenceEnumStringKey = encryptedEnumPreference("key", StringKeyEnum.Value2)
    val PreferenceEnumIntKey = encryptedEnumPreference("key", IntKeyEnum.Value2)
    val PreferenceEnumOrdinalKey = encryptedEnumPreference("key", Enum.Value2)
    val PreferenceEnumNameKey = encryptedEnumPreference("key", Enum.Value2)
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
