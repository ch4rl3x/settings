package de.charlex.settings.datastore.encryption

import androidx.datastore.core.CorruptionException
import de.charlex.settings.datastore.SettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.security.KeyStore

abstract class SettingsDataStoreEncryptionTest {

    lateinit var settings: SettingsDataStore

    @Test
    fun test_Int_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
        Assert.assertEquals(1, settings.get(EncryptedPreferences.PreferenceInt).first())

        settings.put(EncryptedPreferences.PreferenceInt, 10)
        Assert.assertEquals(10, settings.get(EncryptedPreferences.PreferenceInt).first())
    }

    @Test
    fun test_String_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceString, EncryptedPreferences.PreferenceString.defaultValue)
        Assert.assertEquals("default", settings.get(EncryptedPreferences.PreferenceString).first())

        settings.put(EncryptedPreferences.PreferenceString, "test")
        Assert.assertEquals("test", settings.get(EncryptedPreferences.PreferenceString).first())
    }

    @Test
    fun test_Float_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceFloat, EncryptedPreferences.PreferenceFloat.defaultValue)
        Assert.assertEquals(1.1f, settings.get(EncryptedPreferences.PreferenceFloat).first())

        settings.put(EncryptedPreferences.PreferenceFloat, 2.2f)
        Assert.assertEquals(2.2f, settings.get(EncryptedPreferences.PreferenceFloat).first())
    }

    @Test
    fun test_Double_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceDouble, EncryptedPreferences.PreferenceDouble.defaultValue)
        Assert.assertEquals(1.1, settings.get(EncryptedPreferences.PreferenceDouble).first(), 0.0)

        settings.put(EncryptedPreferences.PreferenceDouble, 2.2)
        Assert.assertEquals(2.2, settings.get(EncryptedPreferences.PreferenceDouble).first(), 0.0)
    }

    @Test
    fun test_Long_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceLong, EncryptedPreferences.PreferenceLong.defaultValue)
        Assert.assertEquals(1L, settings.get(EncryptedPreferences.PreferenceLong).first())

        settings.put(EncryptedPreferences.PreferenceLong, 2L)
        Assert.assertEquals(2L, settings.get(EncryptedPreferences.PreferenceLong).first())
    }

    @Test
    fun test_Boolean_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceBoolean, EncryptedPreferences.PreferenceBoolean.defaultValue)
        Assert.assertEquals(true, settings.get(EncryptedPreferences.PreferenceBoolean).first())

        settings.put(EncryptedPreferences.PreferenceBoolean, false)
        Assert.assertEquals(false, settings.get(EncryptedPreferences.PreferenceBoolean).first())
    }

    @Test
    fun test_Enum_String_Key_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnumStringKey, EncryptedPreferences.PreferenceEnumStringKey.defaultValue)
        Assert.assertEquals(StringKeyEnum.Value2, settings.get(EncryptedPreferences.PreferenceEnumStringKey).first())

        settings.put(EncryptedPreferences.PreferenceEnumStringKey, StringKeyEnum.Value1)
        Assert.assertEquals(StringKeyEnum.Value1, settings.get(EncryptedPreferences.PreferenceEnumStringKey).first())
    }

    @Test
    fun test_Enum_Int_Key_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnumIntKey, EncryptedPreferences.PreferenceEnumIntKey.defaultValue)
        Assert.assertEquals(IntKeyEnum.Value2, settings.get(EncryptedPreferences.PreferenceEnumIntKey).first())

        settings.put(EncryptedPreferences.PreferenceEnumIntKey, IntKeyEnum.Value1)
        Assert.assertEquals(IntKeyEnum.Value1, settings.get(EncryptedPreferences.PreferenceEnumIntKey).first())
    }

    @Test
    fun test_Enum_Ordinal_Key_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnumOrdinalKey, EncryptedPreferences.PreferenceEnumOrdinalKey.defaultValue)
        Assert.assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumOrdinalKey).first())

        settings.put(EncryptedPreferences.PreferenceEnumOrdinalKey, Enum.Value1)
        Assert.assertEquals(Enum.Value1, settings.get(EncryptedPreferences.PreferenceEnumOrdinalKey).first())
    }

    @Test
    fun test_Enum_Name_Key_Settings() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceEnumNameKey, EncryptedPreferences.PreferenceEnumNameKey.defaultValue)
        Assert.assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumNameKey).first())

        settings.put(EncryptedPreferences.PreferenceEnumNameKey, Enum.Value1)
        Assert.assertEquals(Enum.Value1, settings.get(EncryptedPreferences.PreferenceEnumNameKey).first())
    }

    @Test
    fun testMultipleEncryption() = runBlocking {
        settings.put(EncryptedPreferences.PreferenceString, "A")
        settings.put(EncryptedPreferences.PreferenceString2, "B")
        settings.put(EncryptedPreferences.PreferenceString3, "C")
        Assert.assertEquals("A", settings.get(EncryptedPreferences.PreferenceString).first())
        Assert.assertEquals("B", settings.get(EncryptedPreferences.PreferenceString2).first())
        Assert.assertEquals("C", settings.get(EncryptedPreferences.PreferenceString3).first())
    }

    @Test(
        expected = Throwable::class
    )
    fun testTypeError_Generic(): Unit = runBlocking {
        settings.put(EncryptedPreferences.PreferenceInt, EncryptedPreferences.PreferenceInt.defaultValue)
        val intFlow = settings.get(EncryptedPreferences.PreferenceInt)

        settings.put(EncryptedPreferences.PreferenceString_WithIntKey, "TEST2")
        intFlow.first()
    }

    @Test
    fun removeEnum() = runBlocking {
        // prepare
        settings.put(EncryptedPreferences.PreferenceEnumNameKey, Enum.Value1)

        // execute
        settings.remove(EncryptedPreferences.PreferenceEnumNameKey)

        // verify
        // should be reset to default when calling get
        Assert.assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumNameKey).first())
    }

    @Test
    fun clear() = runBlocking {
        // prepare
        settings.put(EncryptedPreferences.PreferenceString, "test")
        settings.put(EncryptedPreferences.PreferenceEnumNameKey, Enum.Value1)

        // execute
        settings.clear()

        // verify
        Assert.assertEquals(Enum.Value2, settings.get(EncryptedPreferences.PreferenceEnumNameKey).first())
        Assert.assertEquals("default", settings.get(EncryptedPreferences.PreferenceString).first())
    }

    /**
     * On some Samsung devices, we were getting a null SecretKey some times.
     * As we only retrieve keys AFTER we generated them (and wrote something to the datastore),
     * this suggests, the key is lost between saving data to prefs and reading it.
     *
     * This mimics the suggested behaviour of a device which looses the key.
     */
    @Test(expected = CorruptionException::class)
    fun decryptionFailure(): Unit = runBlocking {
        settings.put(EncryptedPreferences.PreferenceString, "my value")

        // now we loose the key!
        KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }.deleteEntry("data-store")

        settings.get(EncryptedPreferences.PreferenceString).first()
    }
}
