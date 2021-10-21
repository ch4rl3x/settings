package de.charlex.settings.datastore

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

abstract class SettingsDataStoreTest {

    lateinit var settings: SettingsDataStore

    @Test
    fun test_Int_Settings() = runBlocking {
        settings.put(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.get(Preferences.PreferenceInt).first())

        settings.put(Preferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.get(Preferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Settings() = runBlocking {
        settings.put(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.get(Preferences.PreferenceString).first())

        settings.put(Preferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.get(Preferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Settings() = runBlocking {
        settings.put(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.get(Preferences.PreferenceFloat).first())

        settings.put(Preferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.get(Preferences.PreferenceFloat).first())
    }

    @Test
    fun test_Double_Settings() = runBlocking {
        settings.put(Preferences.PreferenceDouble, Preferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.get(Preferences.PreferenceDouble).first(), 0.0)

        settings.put(Preferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.get(Preferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Long_Settings() = runBlocking {
        settings.put(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.get(Preferences.PreferenceLong).first())

        settings.put(Preferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.get(Preferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Settings() = runBlocking {
        settings.put(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.get(Preferences.PreferenceBoolean).first())

        settings.put(Preferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.get(Preferences.PreferenceBoolean).first())
    }

    @Test
    fun test_Enum_Settings() = runBlocking {
        settings.put(Preferences.PreferenceEnum, Preferences.PreferenceEnum.defaultValue)
        Assert.assertEquals(Speed.Medium, settings.get(Preferences.PreferenceEnum).first())

        settings.put(Preferences.PreferenceEnum, Speed.Slow)
        Assert.assertEquals(Speed.Slow, settings.get(Preferences.PreferenceEnum).first())
    }

    @Test
    fun testTypeError() {
        runBlocking {
            settings.put(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
            val intFlow = settings.get(Preferences.PreferenceInt)

            settings.put(Preferences.PreferenceString_WithIntKey, "TEST2")

            try {
                intFlow.first() as Int
                Assert.fail("Exception expected")
            } catch (e: ClassCastException) {
                true
            }
        }
    }
}
