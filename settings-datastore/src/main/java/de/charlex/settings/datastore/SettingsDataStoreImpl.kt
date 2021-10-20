package de.charlex.settings.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import de.charlex.settings.core.IPreference
import de.charlex.settings.core.Keyed
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

    override fun getRaw(key: String): Flow<String?> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[stringPreferencesKey(key)]
        }

    override fun getString(pref: IPreference<String>): Flow<String> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[stringPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun getInt(pref: IPreference<Int>): Flow<Int> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[intPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun getFloat(pref: IPreference<Float>): Flow<Float> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[floatPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun getDouble(pref: IPreference<Double>): Flow<Double> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[doublePreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun getBoolean(pref: IPreference<Boolean>): Flow<Boolean> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[booleanPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun getLong(pref: IPreference<Long>): Flow<Long> = context.dataStore.data
        .map { currentPreferences ->
            currentPreferences[longPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override suspend fun putRaw(key: String, value: String) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun putString(pref: IPreference<String>, value: String) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putInt(pref: IPreference<Int>, value: Int) {
        context.dataStore.edit { settings ->
            settings[intPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putFloat(pref: IPreference<Float>, value: Float) {
        context.dataStore.edit { settings ->
            settings[floatPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putDouble(pref: IPreference<Double>, value: Double) {
        context.dataStore.edit { settings ->
            settings[doublePreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putLong(pref: IPreference<Long>, value: Long) {
        context.dataStore.edit { settings ->
            settings[longPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun <T> putEnum(pref: IPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(pref.preferenceKey)] = value.key
        }
    }
}
