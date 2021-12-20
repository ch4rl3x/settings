package de.charlex.settings.compose.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.charlex.settings.compose.datastore.ui.theme.SettingsTheme
import de.charlex.settings.datastore.SettingsDataStore
import de.charlex.settings.datastore.booleanPreference
import de.charlex.settings.datastore.stringPreference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settingsDataStore = SettingsDataStore.create(this)
        setContent {
            Body(settingsDataStore = settingsDataStore)
        }
    }
}

@Composable
fun Body(settingsDataStore: SettingsDataStore) {
    SettingsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CompositionLocalProvider(LocalSettingsDataStore provides settingsDataStore) {
                Settings {
                    string(stringPreference("email", "max.mustermann@googlemail.com"), "Email")
                    boolean(booleanPreference("key1", false), "Label Key")
                    boolean(booleanPreference("key2", false), "Label Key 2")
                    string(stringPreference("email_two", "maria.mustermann@googlemail.com"), "2. Email ")

                    group("Nested Settings") {
                        boolean(booleanPreference("key3", false), "Label Key 3")
                        boolean(booleanPreference("key3a", false), "Label Key 3a")
                    }

                    boolean(booleanPreference("key5", false), "Label Key 5")
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SettingsTheme {
        Body(settingsDataStore = SettingsDataStore.createInMemory())
    }
}