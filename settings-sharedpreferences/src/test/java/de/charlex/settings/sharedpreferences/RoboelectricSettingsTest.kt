package de.charlex.settings.sharedpreferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoboelectricSettingsTest : SettingsTest() {

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        settings = Settings.create(context)
    }
}
