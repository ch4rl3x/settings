package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.core.encryption.EncryptedPreferences
import de.charlex.settings.core.encryption.Speed
import org.junit.Assert.assertEquals
import org.junit.Test

abstract class SettingsEncryptionTest {

    lateinit var settings: EncryptedSettings

    @Test
    fun test_Int_Settings() {
        settings.putInt(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
        assertEquals(1, settings.getInt(EncryptedPreferences.PreferenceInt))

        settings.putInt(EncryptedPreferences.PreferenceInt, 10)
        assertEquals(10, settings.getInt(EncryptedPreferences.PreferenceInt))
    }

    @Test
    fun test_String_Settings() {
        settings.putString(EncryptedPreferences.PreferenceString, EncryptedPreferences.PreferenceString.defaultValue)
        assertEquals("default", settings.getString(EncryptedPreferences.PreferenceString))

        settings.putString(EncryptedPreferences.PreferenceString, "test")
        assertEquals("test", settings.getString(EncryptedPreferences.PreferenceString))
    }

    @Test
    fun test_Float_Settings() {
        settings.putFloat(EncryptedPreferences.PreferenceFloat, EncryptedPreferences.PreferenceFloat.defaultValue)
        assertEquals(1.1f, settings.getFloat(EncryptedPreferences.PreferenceFloat))

        settings.putFloat(EncryptedPreferences.PreferenceFloat, 2.2f)
        assertEquals(2.2f, settings.getFloat(EncryptedPreferences.PreferenceFloat))
    }

    @Test
    fun test_Double_Settings() {
        settings.putDouble(EncryptedPreferences.PreferenceDouble, EncryptedPreferences.PreferenceDouble.defaultValue)
        assertEquals(1.1, settings.getDouble(EncryptedPreferences.PreferenceDouble), 0.0)

        settings.putDouble(EncryptedPreferences.PreferenceDouble, 2.2)
        assertEquals(2.2, settings.getDouble(EncryptedPreferences.PreferenceDouble), 0.0)
    }

    @Test
    fun test_Long_Settings() {
        settings.putLong(EncryptedPreferences.PreferenceLong, EncryptedPreferences.PreferenceLong.defaultValue)
        assertEquals(1L, settings.getLong(EncryptedPreferences.PreferenceLong))

        settings.putLong(EncryptedPreferences.PreferenceLong, 2L)
        assertEquals(2L, settings.getLong(EncryptedPreferences.PreferenceLong))
    }

    @Test
    fun test_Boolean_Settings() {
        settings.putBoolean(EncryptedPreferences.PreferenceBoolean, EncryptedPreferences.PreferenceBoolean.defaultValue)
        assertEquals(true, settings.getBoolean(EncryptedPreferences.PreferenceBoolean))

        settings.putBoolean(EncryptedPreferences.PreferenceBoolean, false)
        assertEquals(false, settings.getBoolean(EncryptedPreferences.PreferenceBoolean))
    }

    @Test
    fun test_ComplexPreference_Settings() {
        settings.putEnum(EncryptedPreferences.PreferenceEnum, EncryptedPreferences.PreferenceEnum.defaultValue)
        assertEquals("medium", settings.getEnum(EncryptedPreferences.PreferenceEnum))

        settings.putEnum(EncryptedPreferences.PreferenceEnum, Speed.Slow)
        assertEquals("slow", settings.getEnum(EncryptedPreferences.PreferenceEnum))
    }
}
