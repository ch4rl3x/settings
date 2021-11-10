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
    fun test_StringSet_Settings() = runBlocking {
        settings.put(Preferences.PreferenceStringSet, Preferences.PreferenceStringSet.defaultValue)
        Assert.assertEquals(setOf<String>(), settings.get(Preferences.PreferenceStringSet).first())

        settings.put(Preferences.PreferenceStringSet, setOf("1", "2", "3"))
        Assert.assertEquals(setOf("1", "2", "3"), settings.get(Preferences.PreferenceStringSet).first())
    }

    @Test
    fun test_Enum_String_Key_Settings() = runBlocking {
        settings.put(Preferences.PreferenceEnumStringKey, Preferences.PreferenceEnumStringKey.defaultValue)
        Assert.assertEquals(StringKeyEnum.Value2, settings.get(Preferences.PreferenceEnumStringKey).first())

        settings.put(Preferences.PreferenceEnumStringKey, StringKeyEnum.Value1)
        Assert.assertEquals(StringKeyEnum.Value1, settings.get(Preferences.PreferenceEnumStringKey).first())
    }

    @Test
    fun test_Enum_Int_Key_Settings() = runBlocking {
        settings.put(Preferences.PreferenceEnumIntKey, Preferences.PreferenceEnumIntKey.defaultValue)
        Assert.assertEquals(IntKeyEnum.Value2, settings.get(Preferences.PreferenceEnumIntKey).first())

        settings.put(Preferences.PreferenceEnumIntKey, IntKeyEnum.Value1)
        Assert.assertEquals(IntKeyEnum.Value1, settings.get(Preferences.PreferenceEnumIntKey).first())
    }

    @Test
    fun test_Enum_Ordinal_Key_Settings() = runBlocking {
        settings.put(Preferences.PreferenceEnumOrdinalKey, Preferences.PreferenceEnumOrdinalKey.defaultValue)
        Assert.assertEquals(Enum.Value2, settings.get(Preferences.PreferenceEnumOrdinalKey).first())

        settings.put(Preferences.PreferenceEnumOrdinalKey, Enum.Value1)
        Assert.assertEquals(Enum.Value1, settings.get(Preferences.PreferenceEnumOrdinalKey).first())
    }

    @Test
    fun test_Enum_Name_Key_Settings() = runBlocking {
        settings.put(Preferences.PreferenceEnumNameKey, Preferences.PreferenceEnumNameKey.defaultValue)
        Assert.assertEquals(Enum.Value2, settings.get(Preferences.PreferenceEnumNameKey).first())

        settings.put(Preferences.PreferenceEnumNameKey, Enum.Value1)
        Assert.assertEquals(Enum.Value1, settings.get(Preferences.PreferenceEnumNameKey).first())
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

    @Test
    fun removeEnum() = runBlocking {
        // prepare
        settings.put(Preferences.PreferenceEnumNameKey, Enum.Value1)

        // execute
        settings.remove(Preferences.PreferenceEnumNameKey)

        // verify
        // should be reset to default when calling get
        Assert.assertEquals(Enum.Value2, settings.get(Preferences.PreferenceEnumNameKey).first())
    }
}
