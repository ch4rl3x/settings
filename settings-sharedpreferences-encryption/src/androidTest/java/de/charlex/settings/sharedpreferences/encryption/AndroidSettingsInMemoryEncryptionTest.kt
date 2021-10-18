package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Settings
import org.junit.Before


class AndroidSettingsInMemoryEncryptionTest : SettingsEncryptionTest() {

    @Before
    fun setup() {
        settings = Settings.createInMemoryEncrypted()
    }
}
