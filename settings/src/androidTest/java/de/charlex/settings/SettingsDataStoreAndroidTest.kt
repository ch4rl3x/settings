package de.charlex.settings

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsDataStoreAndroidTest : SettingsDataStoreTest() {

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        settings = SettingsDataStoreImpl(context)
    }
}