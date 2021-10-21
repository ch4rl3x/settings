package de.charlex.settings.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStoreImpl internal constructor(
    private val context: Context,
    name: String,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
    migrations: (Context) -> List<DataMigration<Preferences>>,
    scope: CoroutineScope
) : SettingsDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = name,
        corruptionHandler = corruptionHandler,
        produceMigrations = migrations,
        scope = scope
    )

    override fun <T> get(key: IDataStorePreference<T>): Flow<T>  {
        return context.dataStore.data.map {
            it[key.preferenceKey] ?: key.defaultValue
        }
    }


    override suspend fun <T> put(key: IDataStorePreference<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key.preferenceKey] = value
        }
    }

    override suspend fun <T> put(key: IDataStoreEnumPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        context.dataStore.edit { settings ->
            settings[key.preferenceKey] = value.key
        }
    }

}

