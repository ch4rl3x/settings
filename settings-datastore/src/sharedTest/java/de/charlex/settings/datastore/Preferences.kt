package de.charlex.settings.datastore

object Preferences {

    val PreferenceInt = intPreference("preference_int", 1)
    val PreferenceString = stringPreference("preference_string", "default")
    val PreferenceString_WithIntKey = stringPreference("preference_int", "default")
    val PreferenceFloat = floatPreference("preference_float", 1.1f)
    val PreferenceDouble = doublePreference("preference_double", 1.1)
    val PreferenceLong = longPreference("preference_long", 1L)
    val PreferenceBoolean = booleanPreference("preference_boolean", true)

    val PreferenceEnum = enumPreference("key", Speed.Medium)
}
