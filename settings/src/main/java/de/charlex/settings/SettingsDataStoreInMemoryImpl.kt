package de.charlex.settings

import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class SettingsDataStoreInMemoryImpl : SettingsDataStore {

    private val flows = mutableMapOf<Preferences.Key<*>, MutableStateFlow<*>>()

    override fun getString(pref: IPreference<String>): Flow<String> {
        val stateFlow = flows.getOrPut(stringPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) })
        return stateFlow.map { it as String }
    }

    override fun getInt(pref: IPreference<Int>): Flow<Int> {
        val stateFlow = flows.getOrPut(intPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) })
        return stateFlow.map { it as Int }
    }

    override fun getFloat(pref: IPreference<Float>): Flow<Float> {
        val stateFlow = flows.getOrPut(floatPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) })
        return stateFlow.map { it as Float }
    }

    override fun getBoolean(pref: IPreference<Boolean>): Flow<Boolean> {
        val stateFlow = flows.getOrPut(booleanPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) })
        return stateFlow.map { it as Boolean }
    }

    override fun getLong(pref: IPreference<Long>): Flow<Long> {
        val stateFlow = flows.getOrPut(longPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) })
        return stateFlow.map { it as Long }
    }

    override suspend fun putString(value: IPreferenceValue<String>) {
        val stateFlow = flows.getOrPut(stringPreferencesKey(value.preferenceKey), { MutableStateFlow(value.value) }) as MutableStateFlow<String>
        stateFlow.value = value.value
    }

    override suspend fun putString(pref: IPreference<String>, value: String) {
        val stateFlow = flows.getOrPut(stringPreferencesKey(pref.preferenceKey), { MutableStateFlow(value) }) as MutableStateFlow<String>
        stateFlow.value = value
    }

    override suspend fun putInt(value: IPreferenceValue<Int>) {
        val stateFlow = flows.getOrPut(intPreferencesKey(value.preferenceKey), { MutableStateFlow(value.value) }) as MutableStateFlow<Int>
        stateFlow.value = value.value
    }

    override suspend fun putInt(pref: IPreference<Int>, value: Int) {
        val stateFlow = flows.getOrPut(intPreferencesKey(pref.preferenceKey), { MutableStateFlow(value) }) as MutableStateFlow<Int>
        stateFlow.value = value
    }

    override suspend fun putFloat(value: IPreferenceValue<Float>) {
        val stateFlow = flows.getOrPut(floatPreferencesKey(value.preferenceKey), { MutableStateFlow(value.value) }) as MutableStateFlow<Float>
        stateFlow.value = value.value
    }

    override suspend fun putFloat(pref: IPreference<Float>, value: Float) {
        val stateFlow = flows.getOrPut(floatPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) }) as MutableStateFlow<Float>
        stateFlow.value = value
    }

    override suspend fun putBoolean(value: IPreferenceValue<Boolean>) {
        val stateFlow = flows.getOrPut(booleanPreferencesKey(value.preferenceKey), { MutableStateFlow(value.value) }) as MutableStateFlow<Boolean>
        stateFlow.value = value.value
    }

    override suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean) {
        val stateFlow = flows.getOrPut(booleanPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) }) as MutableStateFlow<Boolean>
        stateFlow.value = value
    }

    override suspend fun putLong(value: IPreferenceValue<Long>) {
        val stateFlow = flows.getOrPut(longPreferencesKey(value.preferenceKey), { MutableStateFlow(value.value) }) as MutableStateFlow<Long>
        stateFlow.value = value.value
    }

    override suspend fun putLong(pref: IPreference<Long>, value: Long) {
        val stateFlow = flows.getOrPut(longPreferencesKey(pref.preferenceKey), { MutableStateFlow(pref.defaultValue) }) as MutableStateFlow<Long>
        stateFlow.value = value
    }
}
