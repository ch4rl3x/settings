package de.charlex.settings.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsDataStore {

    fun <T> get(key: IDataStorePreference<T>): Flow<T>
    suspend fun <T> put(key: IDataStorePreference<T>, value: T)
    suspend fun <T : Enum<T>, U> put(key: IDataStoreEnumPreference<T, U>, value: T)

    suspend fun <T> remove(pref: IDataStorePreference<T>)
    suspend fun <T : Enum<T>, U> remove(pref: IDataStoreEnumPreference<T, U>)

    companion object {

        private var settingsDataStore: SettingsDataStore? = null

        @JvmOverloads
        fun create(
            context: Context,
            name: String = "settings",
            corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
            migrations: (Context) -> List<DataMigration<Preferences>> = { listOf() },
            scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        ): SettingsDataStore {
            if (settingsDataStore == null) {
                settingsDataStore = SettingsDataStoreImpl(
                    context = context,
                    name = name,
                    corruptionHandler = corruptionHandler,
                    migrations = migrations,
                    scope = scope
                )
            }
            return settingsDataStore!!
        }

        fun createInMemory(): SettingsDataStore {
            return SettingsDataStoreInMemoryImpl()
        }
    }
}

inline fun <reified T : Enum<T>, U> SettingsDataStore.get(pref: IDataStoreEnumPreference<T, U>): Flow<T> {
    val keyProperty = pref.keyProperty
    val preference = when (val defaultValue = keyProperty.call(pref.defaultValue)) {
        is String -> stringPreference(pref.preferenceKey.name, defaultValue as String)
        is Int -> intPreference(pref.preferenceKey.name, defaultValue as Int)
        is Float -> floatPreference(pref.preferenceKey.name, defaultValue as Float)
        is Long -> longPreference(pref.preferenceKey.name, defaultValue as Long)
        is Boolean -> booleanPreference(pref.preferenceKey.name, defaultValue as Boolean)
        else -> error("No valid enum key: $defaultValue")
    }
    return get(preference).map { prefValue ->
        enumValues<T>().find { pref.keyProperty.call(it) == prefValue } ?: pref.defaultValue
    }
}
