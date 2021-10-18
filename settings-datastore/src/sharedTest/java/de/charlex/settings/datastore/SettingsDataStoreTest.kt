package de.charlex.settings.datastore

import de.charlex.settings.core.PreferenceValue
import de.charlex.settings.core.Preferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

abstract class SettingsDataStoreTest {

    lateinit var settings: SettingsDataStore

    @Test
    fun test_Int_Settings() = runBlocking {
        settings.putInt(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.getInt(Preferences.PreferenceInt).first())

        settings.putInt(Preferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.getInt(Preferences.PreferenceInt).first())

        settings.putInt(PreferenceValue(Preferences.PreferenceInt, 20))
        Assert.assertEquals(20, settings.getInt(Preferences.PreferenceInt).first())
    }

    @Test
    fun test_Int_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.get(Preferences.PreferenceInt).first())

        settings.put(Preferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.get(Preferences.PreferenceInt).first())

        settings.put(PreferenceValue(Preferences.PreferenceInt, 20))
        Assert.assertEquals(20, settings.get(Preferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Settings() = runBlocking {
        settings.putString(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.getString(Preferences.PreferenceString).first())

        settings.putString(Preferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.getString(Preferences.PreferenceString).first())

        settings.putString(
            PreferenceValue(
                Preferences.PreferenceString,
                "value"
            )
        )
        Assert.assertEquals("value", settings.getString(Preferences.PreferenceString).first())
    }

    @Test
    fun test_String_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceString, Preferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.get(Preferences.PreferenceString).first())

        settings.put(Preferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.get(Preferences.PreferenceString).first())

        settings.put(
            PreferenceValue(
                Preferences.PreferenceString,
                "value"
            )
        )
        Assert.assertEquals("value", settings.get(Preferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Settings() = runBlocking {
        settings.putFloat(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.getFloat(Preferences.PreferenceFloat).first())

        settings.putFloat(Preferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.getFloat(Preferences.PreferenceFloat).first())

        settings.putFloat(PreferenceValue(Preferences.PreferenceFloat, 3.3f))
        Assert.assertEquals(3.3f, settings.getFloat(Preferences.PreferenceFloat).first())
    }

    @Test
    fun test_Float_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceFloat, Preferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.get(Preferences.PreferenceFloat).first())

        settings.put(Preferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.get(Preferences.PreferenceFloat).first())

        settings.put(PreferenceValue(Preferences.PreferenceFloat, 3.3f))
        Assert.assertEquals(3.3f, settings.get(Preferences.PreferenceFloat).first())
    }

    @Test
    fun test_Double_Settings() = runBlocking {
        settings.putDouble(Preferences.PreferenceDouble, Preferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.getDouble(Preferences.PreferenceDouble).first(), 0.0)

        settings.putDouble(Preferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.getDouble(Preferences.PreferenceDouble).first(), 0.0)

        settings.putDouble(PreferenceValue(Preferences.PreferenceDouble, 3.3))
        Assert.assertEquals(3.3, settings.getDouble(Preferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Double_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceDouble, Preferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.get(Preferences.PreferenceDouble).first(), 0.0)

        settings.put(Preferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.get(Preferences.PreferenceDouble).first(), 0.0)

        settings.put(PreferenceValue(Preferences.PreferenceDouble, 3.3))
        Assert.assertEquals(3.3, settings.get(Preferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Long_Settings() = runBlocking {
        settings.putLong(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.getLong(Preferences.PreferenceLong).first())

        settings.putLong(Preferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.getLong(Preferences.PreferenceLong).first())

        settings.putLong(PreferenceValue(Preferences.PreferenceLong, 3L))
        Assert.assertEquals(3L, settings.getLong(Preferences.PreferenceLong).first())
    }

    @Test
    fun test_Long_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceLong, Preferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.get(Preferences.PreferenceLong).first())

        settings.put(Preferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.get(Preferences.PreferenceLong).first())

        settings.put(PreferenceValue(Preferences.PreferenceLong, 3L))
        Assert.assertEquals(3L, settings.get(Preferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Settings() = runBlocking {
        settings.putBoolean(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.getBoolean(Preferences.PreferenceBoolean).first())

        settings.putBoolean(Preferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.getBoolean(Preferences.PreferenceBoolean).first())

        settings.putBoolean(
            PreferenceValue(
                Preferences.PreferenceBoolean,
                true
            )
        )
        Assert.assertEquals(true, settings.getBoolean(Preferences.PreferenceBoolean).first())
    }

    @Test
    fun test_Boolean_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceBoolean, Preferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.get(Preferences.PreferenceBoolean).first())

        settings.put(Preferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.get(Preferences.PreferenceBoolean).first())

        settings.put(
            PreferenceValue(
                Preferences.PreferenceBoolean,
                true
            )
        )
        Assert.assertEquals(true, settings.get(Preferences.PreferenceBoolean).first())
    }

    @Test
    fun test_ComplexPreference_Settings() = runBlocking {
        settings.putString(Preferences.PreferenceComplex, Preferences.PreferenceComplex.defaultValue)
        Assert.assertEquals("medium", settings.getString(Preferences.PreferenceComplex).first())

        settings.putString(Preferences.PreferenceComplex.Slow)
        Assert.assertEquals("slow", settings.getString(Preferences.PreferenceComplex).first())

        settings.putString(
            PreferenceValue(
                Preferences.PreferenceComplex,
                "fast"
            )
        )
        Assert.assertEquals("fast", settings.getString(Preferences.PreferenceComplex).first())
    }

    @Test
    fun test_ComplexPreference_Generic_Settings() = runBlocking {
        settings.put(Preferences.PreferenceComplex, Preferences.PreferenceComplex.defaultValue)
        Assert.assertEquals("medium", settings.get(Preferences.PreferenceComplex).first())

        settings.put(Preferences.PreferenceComplex.Slow)
        Assert.assertEquals("slow", settings.get(Preferences.PreferenceComplex).first())

        settings.put(
            PreferenceValue(
                Preferences.PreferenceComplex,
                "fast"
            )
        )
        Assert.assertEquals("fast", settings.get(Preferences.PreferenceComplex).first())
    }

    @Test
    fun testTypeError() {
        runBlocking {
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

    @Test
    fun testTypeError_Generic() {
        runBlocking {
            settings.put(Preferences.PreferenceInt, Preferences.PreferenceInt.defaultValue)
            val intFlow = settings.get(Preferences.PreferenceInt)

            settings.put(Preferences.PreferenceString_WithIntKey, "TEST2")

            try {
                intFlow.first()
                Assert.fail("Exception expected")
            } catch (e: ClassCastException) {
                true
            }
        }
    }
}
