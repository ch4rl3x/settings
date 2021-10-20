package de.charlex.settings.core

object Preferences {

    val PreferenceInt = Preference("preference_int", 1)
    val PreferenceString = Preference("preference_string", "default")
    val PreferenceString_WithIntKey = Preference("preference_int", "default")
    val PreferenceFloat = Preference("preference_float", 1.1f)
    val PreferenceDouble = Preference("preference_double", 1.1)
    val PreferenceLong = Preference("preference_long", 1L)
    val PreferenceBoolean = Preference("preference_boolean", true)

    val PreferenceEnum = EnumPreference("key", Speed.Medium)
}
