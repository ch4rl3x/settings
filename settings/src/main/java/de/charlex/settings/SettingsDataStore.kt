package de.charlex.settings

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NonNls

interface SettingsDataStore {

    fun getString(pref: IPreference<String>): Flow<String>
    fun getInt(pref: IPreference<Int>): Flow<Int>
    fun getFloat(pref: IPreference<Float>): Flow<Float>
    fun getDouble(pref: IPreference<Double>): Flow<Double>
    fun getBoolean(pref: IPreference<Boolean>): Flow<Boolean>
    fun getLong(pref: IPreference<Long>): Flow<Long>

    suspend fun putString(value: IPreferenceValue<String>)
    suspend fun putInt(value: IPreferenceValue<Int>)
    suspend fun putFloat(value: IPreferenceValue<Float>)
    suspend fun putDouble(value: IPreferenceValue<Double>)
    suspend fun putBoolean(value: IPreferenceValue<Boolean>)
    suspend fun putLong(value: IPreferenceValue<Long>)
    @NonNls
    suspend fun putString(pref: IPreference<String>, value: String)
    suspend fun putInt(pref: IPreference<Int>, value: Int)
    suspend fun putFloat(pref: IPreference<Float>, value: Float)
    suspend fun putDouble(pref: IPreference<Double>, value: Double)
    suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    suspend fun putLong(pref: IPreference<Long>, value: Long)

    companion object {

        var settingsDataStore: SettingsDataStore? = null

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
