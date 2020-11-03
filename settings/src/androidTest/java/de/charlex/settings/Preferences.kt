package de.charlex.settings

object Preferences {
    val PreferenceComplex = SpeedPreference("preference_complex")

    val PreferenceInt = Preference("preference_int", 1)
    val PreferenceString = Preference("preference_string", "default")
    val PreferenceFloat = Preference("preference_float", 1.1f)
    val PreferenceLong = Preference("preference_long", 1L)
    val PreferenceBoolean = Preference("preference_boolean", true)
}
