package de.charlex.settings.sharedpreferences.encryption

object EncryptedPreferences {
    val PreferenceEnum = encryptedEnumPreference("preference_complex", Speed.Medium)

    val PreferenceInt = encryptedIntPreference("preference_int", 1)
    val PreferenceString =
        encryptedStringPreference("preference_string", "default")
    val PreferenceString2 =
        encryptedStringPreference("preference_string_2", "default")
    val PreferenceString3 =
        encryptedStringPreference("preference_string_3", "default")
    val PreferenceString_WithIntKey =
        encryptedStringPreference("preference_int", "default")
    val PreferenceFloat = encryptedFloatPreference("preference_float", 1.1f)
    val PreferenceDouble = encryptedDoublePreference("preference_double", 1.1)
    val PreferenceLong = encryptedLongPreference("preference_long", 1L)
    val PreferenceBoolean = encryptedBooleanPreference("preference_boolean", true)
    val PreferenceStringSet = encryptedStringSetPreference("preference_boolean", setOf("Element 1", "Element 2"))
}
