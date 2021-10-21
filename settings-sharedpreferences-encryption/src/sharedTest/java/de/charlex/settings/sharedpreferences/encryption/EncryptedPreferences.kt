package de.charlex.settings.sharedpreferences.encryption


object EncryptedPreferences {
    val PreferenceEnum = EncryptedEnumPreference("preference_complex", Speed.Medium)

    val PreferenceInt = EncryptedPreference("preference_int", 1)
    val PreferenceString =
        EncryptedPreference("preference_string", "default")
    val PreferenceString2 =
        EncryptedPreference("preference_string_2", "default")
    val PreferenceString3 =
        EncryptedPreference("preference_string_3", "default")
    val PreferenceString_WithIntKey =
        EncryptedPreference("preference_int", "default")
    val PreferenceFloat = EncryptedPreference("preference_float", 1.1f)
    val PreferenceDouble = EncryptedPreference("preference_double", 1.1)
    val PreferenceLong = EncryptedPreference("preference_long", 1L)
    val PreferenceBoolean = EncryptedPreference("preference_boolean", true)
}
