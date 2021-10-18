package de.charlex.settings.datastore

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoboelectricSettingsDataStoreTest : SettingsDataStoreTest() {

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        settings = SettingsDataStore.create(context)
    }
}
