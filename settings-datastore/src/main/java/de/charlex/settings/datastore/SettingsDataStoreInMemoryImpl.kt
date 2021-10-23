package de.charlex.settings.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class SettingsDataStoreInMemoryImpl internal constructor() : SettingsDataStore {

    private val flows = mutableMapOf<Preferences.Key<Any>, MutableStateFlow<Any>>()

    override fun <T> get(key: IDataStorePreference<T>): Flow<T> {
        val stateFlow = flows.getOrPut(key.preferenceKey as Preferences.Key<Any>, { MutableStateFlow(key.defaultValue as Any) }) as Flow<T>
        return stateFlow.map { it }
    }

    override suspend fun <T> put(key: IDataStorePreference<T>, value: T) {
        val stateFlow = flows.getOrPut(key.preferenceKey as Preferences.Key<Any>, { MutableStateFlow(key.defaultValue as Any) }) as MutableStateFlow<T>
        stateFlow.value = value
    }

    override suspend fun <T> put(key: IDataStoreEnumPreference<T>, value: T) where T : Enum<T>, T : Keyed {
        val stateFlow = flows.getOrPut(key.preferenceKey as Preferences.Key<Any>, { MutableStateFlow(key.defaultValue.key) }) as MutableStateFlow<String>
        stateFlow.value = value.key
    }
}
