package de.charlex.settings.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import de.charlex.settings.datastore.security.Security
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStoreImpl internal constructor(
    private val context: Context,
    name: String,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
    migrations: (Context) -> List<DataMigration<Preferences>>,
    scope: CoroutineScope,
    override val security: Security
) : SettingsDataStore, SecurityProvider {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = name,
        corruptionHandler = corruptionHandler,
        produceMigrations = migrations,
        scope = scope
    )

    override fun <T> get(key: IDataStorePreference<T>): Flow<T> {
        return context.dataStore.data.map {
            it[key.preferenceKey] ?: key.defaultValue
        }
    }

    override suspend fun <T> put(key: IDataStorePreference<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key.preferenceKey] = value
        }
    }

    override suspend fun <T : Enum<T>, U> put(key: IDataStoreEnumPreference<T, U>, value: T) {
        context.dataStore.edit { settings ->
            settings[key.preferenceKey] = key.keyProperty.call(value)
        }
    }

    override suspend fun <T> remove(pref: IDataStorePreference<T>) {
        context.dataStore.edit { settings ->
            settings.remove(pref.preferenceKey)
        }
    }

    override suspend fun <T : Enum<T>, U> remove(pref: IDataStoreEnumPreference<T, U>) {
        context.dataStore.edit { settings ->
            settings.remove(pref.preferenceKey)
        }
    }
}
