package de.charlex.settings.datastore.encryption

import de.charlex.settings.datastore.SettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

abstract class SettingsDataStoreEncryptionTest {

    lateinit var settings: SettingsDataStore

    @Test
    fun test_Int_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.get(EncryptedPreferences.PreferenceInt).first())

        settings.put(EncryptedPreferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.get(EncryptedPreferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceString, EncryptedPreferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.get(EncryptedPreferences.PreferenceString).first())

        settings.put(EncryptedPreferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.get(EncryptedPreferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceFloat, EncryptedPreferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.get(EncryptedPreferences.PreferenceFloat).first())

        settings.put(EncryptedPreferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.get(EncryptedPreferences.PreferenceFloat).first())
    }

    @Test
    fun test_Double_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceDouble, EncryptedPreferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.get(EncryptedPreferences.PreferenceDouble).first(), 0.0)

        settings.put(EncryptedPreferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.get(EncryptedPreferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Long_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceLong, EncryptedPreferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.get(EncryptedPreferences.PreferenceLong).first())

        settings.put(EncryptedPreferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.get(EncryptedPreferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceBoolean, EncryptedPreferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.get(EncryptedPreferences.PreferenceBoolean).first())

        settings.put(EncryptedPreferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.get(EncryptedPreferences.PreferenceBoolean).first())
    }

    @Test
    fun test_Enum_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnum, EncryptedPreferences.PreferenceEnum.defaultValue)
        Assert.assertEquals("medium", settings.get(EncryptedPreferences.PreferenceEnum).first())

        settings.put(EncryptedPreferences.PreferenceEnum, Speed.Slow)
        Assert.assertEquals("slow", settings.get(EncryptedPreferences.PreferenceEnum).first())
    }

    @Test
    fun test_Enum_Generic_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnum, EncryptedPreferences.PreferenceEnum.defaultValue)
        Assert.assertEquals("medium", settings.get(EncryptedPreferences.PreferenceEnum).first())

        settings.put(EncryptedPreferences.PreferenceEnum, Speed.Slow)
        Assert.assertEquals("slow", settings.get(EncryptedPreferences.PreferenceEnum).first())
    }

    @Test
    fun testMultipleEncryption() {
        runBlocking {
            settings.put(EncryptedPreferences.PreferenceString, "A")
            settings.put(EncryptedPreferences.PreferenceString2, "B")
            settings.put(EncryptedPreferences.PreferenceString3, "C")
            Assert.assertEquals("A", settings.get(EncryptedPreferences.PreferenceString).first())
            Assert.assertEquals("B", settings.get(EncryptedPreferences.PreferenceString2).first())
            Assert.assertEquals("C", settings.get(EncryptedPreferences.PreferenceString3).first())
        }
    }

    @Test
    fun testTypeError_Generic() {
        runBlocking {
            settings.put(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
            val intFlow = settings.get(EncryptedPreferences.PreferenceInt)

            settings.put(EncryptedPreferences.PreferenceString_WithIntKey, "TEST2")

            try {
                intFlow.first()
                Assert.fail("Exception expected")
            } catch (e: ClassCastException) {
                true
            }
        }
    }
}
