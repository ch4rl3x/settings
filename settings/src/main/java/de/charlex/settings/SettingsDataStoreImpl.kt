package de.charlex.settings

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Deprecated("Still WIP", level = DeprecationLevel.WARNING)
class SettingsDataStoreImpl(
    override val dataStore: DataStore<Preferences>
) : SettingsDataStore {

    constructor(context: Context,
                sharedPreferencesName: String,
                scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())): this(context.createDataStore(
        name = "settings",
        migrations = listOf(SharedPreferencesMigration(context = context, sharedPreferencesName = sharedPreferencesName)),
        scope = scope
    ))

    constructor(context: Context,
                migrations: List<DataMigration<Preferences>> = listOf(),
                scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())): this(context.createDataStore(
        name = "settings",
        migrations = migrations,
        scope = scope
    ))

    override fun createStringFlow(pref: IPreference<String>): Flow<String> = dataStore.data
        .map { currentPreferences ->
            currentPreferences[stringPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun createIntFlow(pref: IPreference<Int>): Flow<Int> = dataStore.data
        .map { currentPreferences ->
            currentPreferences[intPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun createFloatFlow(pref: IPreference<Float>): Flow<Float> = dataStore.data
        .map { currentPreferences ->
            currentPreferences[floatPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun createBooleanFlow(pref: IPreference<Boolean>): Flow<Boolean> = dataStore.data
        .map { currentPreferences ->
            currentPreferences[booleanPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override fun createLongFlow(pref: IPreference<Long>): Flow<Long> = dataStore.data
        .map { currentPreferences ->
            currentPreferences[longPreferencesKey(pref.preferenceKey)] ?: pref.defaultValue
        }

    override suspend fun putString(value: IPreferenceValue<String>) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putInt(value: IPreferenceValue<Int>) {
        dataStore.edit { settings ->
            settings[intPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putFloat(value: IPreferenceValue<Float>) {
        dataStore.edit { settings ->
            settings[floatPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putBoolean(value: IPreferenceValue<Boolean>) {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putLong(value: IPreferenceValue<Long>) {
        dataStore.edit { settings ->
            settings[longPreferencesKey(value.preferenceKey)] = value.value
        }
    }

    override suspend fun putString(pref: IPreference<String>, value: String) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putInt(pref: IPreference<Int>, value: Int) {
        dataStore.edit { settings ->
            settings[intPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putFloat(pref: IPreference<Float>, value: Float) {
        dataStore.edit { settings ->
            settings[floatPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(pref.preferenceKey)] = value
        }
    }

    override suspend fun putLong(pref: IPreference<Long>, value: Long) {
        dataStore.edit { settings ->
            settings[longPreferencesKey(pref.preferenceKey)] = value
        }
    }
}
