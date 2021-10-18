package de.charlex.settings.datastore

import org.junit.Before


class SettingsDataStoreInMemoryTest : SettingsDataStoreTest() {

    @Before
    fun setup() {
        settings = SettingsDataStore.createInMemory()
    }
}
