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
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt,
                20
            )
        )
        Assert.assertEquals(20, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString,
                "value"
            )
        )
        Assert.assertEquals("value", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat,
                3.3f
            )
        )
        Assert.assertEquals(3.3f, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceFloat).first())
    }

    @Test
    fun test_Double_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble).first(), 0.0)

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble).first(), 0.0)

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble,
                3.3
            )
        )
        Assert.assertEquals(3.3, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Long_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong,
                3L
            )
        )
        Assert.assertEquals(3L, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean,
                true
            )
        )
        Assert.assertEquals(true, settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceBoolean).first())
    }

    @Test
    fun test_ComplexPreference_Generic_Settings() = runBlocking {
        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex.defaultValue)
        Assert.assertEquals("medium", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex).first())

        settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex.Slow)
        Assert.assertEquals("slow", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex).first())

        settings.put(
            de.charlex.settings.core.EncryptedPreferenceValue(
                de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex,
                "fast"
            )
        )
        Assert.assertEquals("fast", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceComplex).first())
    }

    @Test
    fun testMultipleEncryption() {
        runBlocking {
            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString, "A")
            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString2, "B")
            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString3, "C")
            Assert.assertEquals("A", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString).first())
            Assert.assertEquals("B", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString2).first())
            Assert.assertEquals("C", settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString3).first())
        }
    }

    @Test
    fun testTypeError_Generic() {
        runBlocking {
            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt, de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt.defaultValue)
            val intFlow = settings.get(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt)

            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceString_WithIntKey, "TEST2")

            try {
                intFlow.first()
                Assert.fail("Exception expected")
            } catch (e: ClassCastException) {
                true
            }
        }
    }

    @Test
    fun testEncryption() {
        runBlocking {
            settings.put(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt, 25)
            val intFlow = settings.getRaw(de.charlex.settings.core.encryption.EncryptedPreferences.PreferenceInt.preferenceKey)

            Assert.assertNotEquals(25, intFlow.first())
        }
    }
}
