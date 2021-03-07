package de.charlex.settings

import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SettingsTestInMemoryTest : SettingsTest() {

    @Before
    fun setup() {
        settings = Settings.createInMemory()
    }
}
