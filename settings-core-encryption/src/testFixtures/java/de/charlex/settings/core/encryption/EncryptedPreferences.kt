package de.charlex.settings.core.encryption

object EncryptedPreferences {
    val PreferenceComplex = EncryptedSpeedPreference("preference_complex")

    val PreferenceInt = de.charlex.settings.core.EncryptedPreference("preference_int", 1)
    val PreferenceString =
        de.charlex.settings.core.EncryptedPreference("preference_string", "default")
    val PreferenceString2 =
        de.charlex.settings.core.EncryptedPreference("preference_string_2", "default")
    val PreferenceString3 =
        de.charlex.settings.core.EncryptedPreference("preference_string_3", "default")
    val PreferenceString_WithIntKey =
        de.charlex.settings.core.EncryptedPreference("preference_int", "default")
    val PreferenceFloat = de.charlex.settings.core.EncryptedPreference("preference_float", 1.1f)
    val PreferenceDouble = de.charlex.settings.core.EncryptedPreference("preference_double", 1.1)
    val PreferenceLong = de.charlex.settings.core.EncryptedPreference("preference_long", 1L)
    val PreferenceBoolean = de.charlex.settings.core.EncryptedPreference("preference_boolean", true)
}
