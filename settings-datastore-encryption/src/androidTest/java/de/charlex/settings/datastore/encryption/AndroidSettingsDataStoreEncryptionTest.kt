package de.charlex.settings.datastore.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import de.charlex.settings.datastore.SettingsDataStore
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AndroidSettingsDataStoreEncryptionTest : SettingsDataStoreEncryptionTest() {

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        settings = SettingsDataStore.create(context)
    }
}
