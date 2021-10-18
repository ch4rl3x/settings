package de.charlex.settings.datastore.encryption

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.charlex.settings.datastore.SettingsDataStore
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Ignore("java.security.KeyStoreException: AndroidKeyStore not found https://github.com/robolectric/robolectric/issues/1518")
class RoboelectricSettingsDataStoreEncryptionTest : SettingsDataStoreEncryptionTest() {

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        settings = SettingsDataStore.create(context)
    }
}
