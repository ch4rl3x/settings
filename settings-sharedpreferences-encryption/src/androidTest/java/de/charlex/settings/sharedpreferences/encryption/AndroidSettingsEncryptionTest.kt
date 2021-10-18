package de.charlex.settings.sharedpreferences.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import de.charlex.settings.sharedpreferences.Settings
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AndroidSettingsEncryptionTest : SettingsEncryptionTest() {

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        settings = Settings.createEncrypted(appContext)
    }


}
