package de.charlex.settings.sharedpreferences

import org.junit.Before


class SettingsInMemoryTest : SettingsTest() {

    @Before
    fun setup() {
        settings = Settings.createInMemory()
    }
}
