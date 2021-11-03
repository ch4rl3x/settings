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
    fun test_Enum_String_Key_Settings() {
        settings.put(Preferences.PreferenceEnumStringKey, Preferences.PreferenceEnumStringKey.defaultValue)
        assertEquals(StringKeyEnum.Value2, settings.get(Preferences.PreferenceEnumStringKey))

        settings.put(Preferences.PreferenceEnumStringKey, StringKeyEnum.Value1)
        assertEquals(StringKeyEnum.Value1, settings.get(Preferences.PreferenceEnumStringKey))
    }

    @Test
    fun test_Enum_Int_Key_Settings() {
        settings.put(Preferences.PreferenceEnumIntKey, Preferences.PreferenceEnumIntKey.defaultValue)
        assertEquals(IntKeyEnum.Value2, settings.get(Preferences.PreferenceEnumIntKey))

        settings.put(Preferences.PreferenceEnumIntKey, IntKeyEnum.Value1)
        assertEquals(IntKeyEnum.Value1, settings.get(Preferences.PreferenceEnumIntKey))
    }

    @Test
    fun test_Enum_Ordinal_Key_Settings() {
        settings.put(Preferences.PreferenceEnumOrdinalKey, Preferences.PreferenceEnumOrdinalKey.defaultValue)
        assertEquals(Enum.Value2, settings.get(Preferences.PreferenceEnumOrdinalKey))

        settings.put(Preferences.PreferenceEnumOrdinalKey, Enum.Value1)
        assertEquals(Enum.Value1, settings.get(Preferences.PreferenceEnumOrdinalKey))
    }

    @Test
    fun test_Enum_Name_Key_Settings() {
        settings.put(Preferences.PreferenceEnumNameKey, Preferences.PreferenceEnumNameKey.defaultValue)
        assertEquals(Enum.Value2, settings.get(Preferences.PreferenceEnumNameKey))

        settings.put(Preferences.PreferenceEnumNameKey, Enum.Value1)
        assertEquals(Enum.Value1, settings.get(Preferences.PreferenceEnumNameKey))
    }
}
