package de.charlex.settings

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
abstract class SettingsTest {

    lateinit var settings: Settings

    @Test
    fun test_Int_Settings() {
        settings.putInt(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        assertEquals(1, settings.getInt(Preferences.PreferenceInt))

        settings.putInt(Preferences.PreferenceInt, 10)
        assertEquals(10, settings.getInt(Preferences.PreferenceInt))

        settings.putInt(PreferenceValue(Preferences.PreferenceInt, 100))
        assertEquals(100, settings.getInt(Preferences.PreferenceInt))
    }

    @Test
    fun test_String_Settings() {
        settings.putString(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        assertEquals("default", settings.getString(Preferences.PreferenceString))

        settings.putString(Preferences.PreferenceString, "test")
        assertEquals("test", settings.getString(Preferences.PreferenceString))

        settings.putString(PreferenceValue(Preferences.PreferenceString, "value"))
        assertEquals("value", settings.getString(Preferences.PreferenceString))
    }

    @Test
    fun test_Float_Settings() {
        settings.putFloat(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        assertEquals(1.1f, settings.getFloat(Preferences.PreferenceFloat))

        settings.putFloat(Preferences.PreferenceFloat, 2.2f)
        assertEquals(2.2f, settings.getFloat(Preferences.PreferenceFloat))

        settings.putFloat(PreferenceValue(Preferences.PreferenceFloat, 3.3f))
        assertEquals(3.3f, settings.getFloat(Preferences.PreferenceFloat))
    }

    @Test
    fun test_Double_Settings() {
        settings.putDouble(Preferences.PreferenceDouble, Preferences.PreferenceDouble.defaultValue)
        assertEquals(1.1, settings.getDouble(Preferences.PreferenceDouble), 0.0)

        settings.putDouble(Preferences.PreferenceDouble, 2.2)
        assertEquals(2.2, settings.getDouble(Preferences.PreferenceDouble), 0.0)

        settings.putDouble(PreferenceValue(Preferences.PreferenceDouble, 3.3))
        assertEquals(3.3, settings.getDouble(Preferences.PreferenceDouble), 0.0)
    }

    @Test
    fun test_Long_Settings() {
        settings.putLong(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        assertEquals(1L, settings.getLong(Preferences.PreferenceLong))

        settings.putLong(Preferences.PreferenceLong, 2L)
        assertEquals(2L, settings.getLong(Preferences.PreferenceLong))

        settings.putLong(PreferenceValue(Preferences.PreferenceLong, 3L))
        assertEquals(3L, settings.getLong(Preferences.PreferenceLong))
    }

    @Test
    fun test_Boolean_Settings() {
        settings.putBoolean(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        assertEquals(true, settings.getBoolean(Preferences.PreferenceBoolean))

        settings.putBoolean(Preferences.PreferenceBoolean, false)
        assertEquals(false, settings.getBoolean(Preferences.PreferenceBoolean))

        settings.putBoolean(PreferenceValue(Preferences.PreferenceBoolean, true))
        assertEquals(true, settings.getBoolean(Preferences.PreferenceBoolean))
    }

    @Test
    fun test_ComplexPreference_Settings() {
        settings.putString(Preferences.PreferenceComplex, Preferences.PreferenceComplex.defaultValue)
        assertEquals("medium", settings.getString(Preferences.PreferenceComplex))

        settings.putString(Preferences.PreferenceComplex.Slow)
        assertEquals("slow", settings.getString(Preferences.PreferenceComplex))

        settings.putString(PreferenceValue(Preferences.PreferenceComplex, "fast"))
        assertEquals("fast", settings.getString(Preferences.PreferenceComplex))
    }
}
