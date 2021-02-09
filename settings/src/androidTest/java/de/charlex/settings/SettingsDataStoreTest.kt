package de.charlex.settings

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

abstract class SettingsDataStoreTest() {

    lateinit var settings: SettingsDataStore

    @Test
    fun test_Int_Settings() = runBlocking {
        settings.putInt(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.getInt(Preferences.PreferenceInt).first())

        settings.putInt(Preferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.getInt(Preferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Settings() = runBlocking {
        settings.putString(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.getString(Preferences.PreferenceString).first())

        settings.putString(Preferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.getString(Preferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Settings() = runBlocking {
        settings.putFloat(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.getFloat(Preferences.PreferenceFloat).first())

        settings.putFloat(Preferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.getFloat(Preferences.PreferenceFloat).first())
    }

    @Test
    fun test_Long_Settings() = runBlocking {
        settings.putLong(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.getLong(Preferences.PreferenceLong).first())

        settings.putLong(Preferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.getLong(Preferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Settings() = runBlocking {
        settings.putBoolean(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.getBoolean(Preferences.PreferenceBoolean).first())

        settings.putBoolean(Preferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.getBoolean(Preferences.PreferenceBoolean).first())
    }

    @Test
    fun test_ComplexPreference_Settings() = runBlocking {
        settings.putString(Preferences.PreferenceComplex, Preferences.PreferenceComplex.defaultValue)
        Assert.assertEquals("medium", settings.getString(Preferences.PreferenceComplex).first())

        settings.putString(Preferences.PreferenceComplex.Slow)
        Assert.assertEquals("slow", settings.getString(Preferences.PreferenceComplex).first())
    }

    @Test
    fun testTypeError() = runBlocking {
        settings.putInt(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        val intFlow = settings.getInt(Preferences.PreferenceInt)

        settings.putString(Preferences.PreferenceString_WithIntKey, "TEST2")

        try {
            intFlow.first()
            Assert.fail("Exception expected")
        } catch (e: ClassCastException) {
            true
        }
    }
}
