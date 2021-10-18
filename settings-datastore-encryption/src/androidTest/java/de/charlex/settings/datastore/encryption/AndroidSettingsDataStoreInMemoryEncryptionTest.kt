package de.charlex.settings.datastore.encryption

import de.charlex.settings.datastore.SettingsDataStore
import org.junit.Before


class AndroidSettingsDataStoreInMemoryEncryptionTest : SettingsDataStoreEncryptionTest() {

    @Before
    fun setup() {
        settings = SettingsDataStore.createInMemory()
    }
}
