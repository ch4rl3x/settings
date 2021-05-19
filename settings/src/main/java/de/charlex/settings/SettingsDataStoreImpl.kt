package de.charlex.settings

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

    override suspend fun putString(value: IPreferenceValue<String>) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putInt(value: IPreferenceValue<Int>) {
        context.dataStore.edit { settings ->
            settings[intPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putFloat(value: IPreferenceValue<Float>) {
        context.dataStore.edit { settings ->
            settings[floatPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putDouble(value: IPreferenceValue<Double>) {
        context.dataStore.edit { settings ->
            settings[doublePreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putBoolean(value: IPreferenceValue<Boolean>) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putLong(value: IPreferenceValue<Long>) {
        context.dataStore.edit { settings ->
            settings[longPreferencesKey(value.preferenceKey)] = value.value
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
}
