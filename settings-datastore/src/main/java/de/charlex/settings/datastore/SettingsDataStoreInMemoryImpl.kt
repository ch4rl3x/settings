package de.charlex.settings.datastore

import androidx.datastore.preferences.core.Preferences
import de.charlex.settings.datastore.security.Security
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class SettingsDataStoreInMemoryImpl internal constructor(
    override val security: Security
) : SettingsDataStore, SecurityProvider {

    private val flows = mutableMapOf<Preferences.Key<*>, MutableStateFlow<Any>>()

    override fun <T> get(key: IDataStorePreference<T>): Flow<T> {
        val stateFlow = flows.getOrPut(key.preferenceKey, { MutableStateFlow(key.defaultValue as Any) }) as Flow<T>
        return stateFlow.map { it }
    }

    override suspend fun <T> put(key: IDataStorePreference<T>, value: T) {
        val stateFlow = flows.getOrPut(key.preferenceKey, { MutableStateFlow(key.defaultValue as Any) }) as MutableStateFlow<T>
        stateFlow.value = value
    }

    override suspend fun <T : Enum<T>, U> put(key: IDataStoreEnumPreference<T, U>, value: T) {
        val stateFlow = flows.getOrPut(key.preferenceKey, { MutableStateFlow(key.keyProperty.call(key.defaultValue) as Any) }) as MutableStateFlow<U>
        stateFlow.value = key.keyProperty.call(value)
    }

    override suspend fun <T> remove(pref: IDataStorePreference<T>) {
        flows.remove(pref.preferenceKey)
    }

    override suspend fun <T : Enum<T>, U> remove(pref: IDataStoreEnumPreference<T, U>) {
        flows.remove(pref.preferenceKey)
    }
}
