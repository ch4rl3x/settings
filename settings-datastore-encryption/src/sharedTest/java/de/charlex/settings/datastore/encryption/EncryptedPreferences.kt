package de.charlex.settings.datastore.encryption

object EncryptedPreferences {

    val PreferenceInt = encryptedIntPreference("preference_int", 1)
    val PreferenceString = encryptedStringPreference("preference_string", "default")
    val PreferenceString2 = encryptedStringPreference("preference_string_2", "default2")
    val PreferenceString3 = encryptedStringPreference("preference_string_3", "default3")
    val PreferenceString_WithIntKey = encryptedStringPreference("preference_int", "default")
    val PreferenceFloat = encryptedFloatPreference("preference_float", 1.1f)
    val PreferenceDouble = encryptedDoublePreference("preference_double", 1.1)
    val PreferenceLong = encryptedLongPreference("preference_long", 1L)
    val PreferenceBoolean = encryptedBooleanPreference("preference_boolean", true)

    val PreferenceEnum = encryptedEnumPreference("key", Speed.Medium)
}