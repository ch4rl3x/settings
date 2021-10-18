package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.charlex.settings.sharedpreferences.Settings
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@Ignore("java.security.KeyStoreException: AndroidKeyStore not found https://github.com/robolectric/robolectric/issues/1518")
class RoboelectricSettingsEncryptionTest : SettingsEncryptionTest() {

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        settings = Settings.createEncrypted(context)
    }
}
