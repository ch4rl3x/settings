package de.charlex.settings.sharedpreferences

import org.junit.Assert.assertEquals
import org.junit.Test

abstract class SettingsTest {

    lateinit var settings: Settings

    @Test
    fun test_Int_Settings() {
        settings.put(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        assertEquals(1, settings.get(Preferences.PreferenceInt))

        settings.put(Preferences.PreferenceInt, 10)
        assertEquals(10, settings.get(Preferences.PreferenceInt))
    }

    @Test
    fun test_String_Settings() {
        settings.put(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        assertEquals("default", settings.get(Preferences.PreferenceString))

        settings.put(Preferences.PreferenceString, "test")
        assertEquals("test", settings.get(Preferences.PreferenceString))
    }

    @Test
    fun test_Float_Settings() {
        settings.put(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        assertEquals(1.1f, settings.get(Preferences.PreferenceFloat))

        settings.put(Preferences.PreferenceFloat, 2.2f)
        assertEquals(2.2f, settings.get(Preferences.PreferenceFloat))
    }

    @Test
    fun test_Long_Settings() {
        settings.put(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        assertEquals(1L, settings.get(Preferences.PreferenceLong))

        settings.put(Preferences.PreferenceLong, 2L)
        assertEquals(2L, settings.get(Preferences.PreferenceLong))
    }

    @Test
    fun test_Boolean_Settings() {
        settings.put(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        assertEquals(true, settings.get(Preferences.PreferenceBoolean))

        settings.put(Preferences.PreferenceBoolean, false)
        assertEquals(false, settings.get(Preferences.PreferenceBoolean))
    }

    @Test
    fun test_StringSet_Settings() {
        settings.put(Preferences.PreferenceStringSet, Preferences.PreferenceStringSet.defaultValue)
        assertEquals(setOf<String>(), settings.get(Preferences.PreferenceStringSet))

        settings.put(Preferences.PreferenceStringSet, setOf("1", "2", "3"))
        assertEquals(setOf("1", "2", "3"), settings.get(Preferences.PreferenceStringSet))
    }

    @Test(
        expected = Throwable::class
    )
    fun test_AnySet_Settings_fail() {
        settings.put(Preferences.PreferenceStringSet, setOf("1", 2, 4) as Set<String>)
    }

    @Test
    fun test_Enum_Settings() {
        settings.put(Preferences.PreferenceEnum, Preferences.PreferenceEnum.defaultValue)
        assertEquals(Speed.Medium, settings.get(Preferences.PreferenceEnum))

        settings.put(Preferences.PreferenceEnum, Speed.Slow)
        assertEquals(Speed.Slow, settings.get(Preferences.PreferenceEnum))
    }
}
