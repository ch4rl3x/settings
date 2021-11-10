package de.charlex.settings.sharedpreferences.encryption

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

abstract class SettingsEncryptionTest {

    lateinit var settings: EncryptedSettings

    @Test
    fun test_Int_Settings() {
        settings.put(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
        assertEquals(1, settings.get(EncryptedPreferences.PreferenceInt))

        settings.put(EncryptedPreferences.PreferenceInt, 10)
        assertEquals(10, settings.get(EncryptedPreferences.PreferenceInt))
    }

    @Test
    fun test_String_Settings() {
        settings.put(EncryptedPreferences.PreferenceString, EncryptedPreferences.PreferenceString.defaultValue)
        assertEquals("default", settings.get(EncryptedPreferences.PreferenceString))

        settings.put(EncryptedPreferences.PreferenceString, "test")
        assertEquals("test", settings.get(EncryptedPreferences.PreferenceString))
    }

    @Test
    fun test_Float_Settings() {
        settings.put(EncryptedPreferences.PreferenceFloat, EncryptedPreferences.PreferenceFloat.defaultValue)
        assertEquals(1.1f, settings.get(EncryptedPreferences.PreferenceFloat))

        settings.put(EncryptedPreferences.PreferenceFloat, 2.2f)
        assertEquals(2.2f, settings.get(EncryptedPreferences.PreferenceFloat))
    }

    @Test
    fun test_Long_Settings() {
        settings.put(EncryptedPreferences.PreferenceLong, EncryptedPreferences.PreferenceLong.defaultValue)
        assertEquals(1L, settings.get(EncryptedPreferences.PreferenceLong))

        settings.put(EncryptedPreferences.PreferenceLong, 2L)
        assertEquals(2L, settings.get(EncryptedPreferences.PreferenceLong))
    }

    @Test
    fun test_Boolean_Settings() {
        settings.put(EncryptedPreferences.PreferenceBoolean, EncryptedPreferences.PreferenceBoolean.defaultValue)
        assertEquals(true, settings.get(EncryptedPreferences.PreferenceBoolean))

        settings.put(EncryptedPreferences.PreferenceBoolean, false)
        assertEquals(false, settings.get(EncryptedPreferences.PreferenceBoolean))
    }

    @Test
    fun test_StringSet_Settings() {
        settings.put(EncryptedPreferences.PreferenceStringSet, EncryptedPreferences.PreferenceStringSet.defaultValue)
        assertArrayEquals(setOf("Element 1", "Element 2").toTypedArray(), settings.get(EncryptedPreferences.PreferenceStringSet).toTypedArray())

        settings.put(EncryptedPreferences.PreferenceStringSet, setOf("Element 1a", "Element 2b", "Element 3c"))
        assertArrayEquals(setOf("Element 1a", "Element 2b", "Element 3c").toTypedArray(), settings.get(EncryptedPreferences.PreferenceStringSet).toTypedArray())
    }

    @Test
    fun test_Enum_Settings() {
        settings.put(EncryptedPreferences.PreferenceEnumStringKey, EncryptedPreferences.PreferenceEnumStringKey.defaultValue)
        assertEquals(StringKeyEnum.Value2, settings.get(EncryptedPreferences.PreferenceEnumStringKey))

        settings.put(EncryptedPreferences.PreferenceEnumStringKey, StringKeyEnum.Value1)
        assertEquals(StringKeyEnum.Value1, settings.get(EncryptedPreferences.PreferenceEnumStringKey))
    }

    @Test
    fun test_Enum_Int_Key_Settings() {
        settings.put(EncryptedPreferences.PreferenceEnumIntKey, EncryptedPreferences.PreferenceEnumIntKey.defaultValue)
        assertEquals(IntKeyEnum.Value2, settings.get(EncryptedPreferences.PreferenceEnumIntKey))

        settings.put(EncryptedPreferences.PreferenceEnumIntKey, IntKeyEnum.Value1)
        assertEquals(IntKeyEnum.Value1, settings.get(EncryptedPreferences.PreferenceEnumIntKey))
    }

    @Test
    fun test_Enum_Ordinal_Key_Settings() {
        settings.put(EncryptedPreferences.PreferenceEnumOrdinalKey, EncryptedPreferences.PreferenceEnumOrdinalKey.defaultValue)
        assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumOrdinalKey))

        settings.put(EncryptedPreferences.PreferenceEnumOrdinalKey, Enum.Value1)
        assertEquals(Enum.Value1, settings.get(EncryptedPreferences.PreferenceEnumOrdinalKey))
    }

    @Test
    fun test_Enum_Name_Key_Settings() {
        settings.put(EncryptedPreferences.PreferenceEnumNameKey, EncryptedPreferences.PreferenceEnumNameKey.defaultValue)
        assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumNameKey))

        settings.put(EncryptedPreferences.PreferenceEnumNameKey, Enum.Value1)
        assertEquals(Enum.Value1, settings.get(EncryptedPreferences.PreferenceEnumNameKey))
    }

    @Test
    fun removeEnum() {
        // prepare
        settings.put(EncryptedPreferences.PreferenceEnumNameKey, Enum.Value1)

        // execute
        settings.remove(EncryptedPreferences.PreferenceEnumNameKey)

        // verify
        // should be reset to default when calling get
        assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumNameKey))
    }
}
