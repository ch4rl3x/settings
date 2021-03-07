package de.charlex.settings

import org.junit.Before

class SettingsDataStoreInMemoryTest : SettingsDataStoreTest() {

    @Before
    fun setup() {
        settings = SettingsDataStore.createInMemory()
    }
}
