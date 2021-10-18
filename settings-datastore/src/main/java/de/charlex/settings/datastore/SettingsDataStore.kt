package de.charlex.settings.datastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import de.charlex.settings.core.IPreference
import de.charlex.settings.core.IPreferenceValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {

    fun getRaw(key: String): Flow<String?>
    suspend fun putRaw(key: String, value: String)

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

    suspend fun putString(pref: IPreference<String>, value: String)
    suspend fun putInt(pref: IPreference<Int>, value: Int)
    suspend fun putFloat(pref: IPreference<Float>, value: Float)
    suspend fun putDouble(pref: IPreference<Double>, value: Double)
    suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    suspend fun putLong(pref: IPreference<Long>, value: Long)

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

@Suppress("UNCHECKED_CAST")
inline fun <reified T> SettingsDataStore.get(pref: IPreference<T>): Flow<T> = when (T::class) {
    String::class ->
        getString(pref = pref as IPreference<String>) as Flow<T>
    Int::class ->
        getInt(pref = pref as IPreference<Int>) as Flow<T>
    Boolean::class ->
        getBoolean(pref = pref as IPreference<Boolean>) as Flow<T>
    Double::class ->
        getDouble(pref = pref as IPreference<Double>) as Flow<T>
    Float::class ->
        getFloat(pref = pref as IPreference<Float>) as Flow<T>
    Long::class ->
        getLong(pref = pref as IPreference<Long>) as Flow<T>
    else ->
        error("No valid preference type ${T::class.simpleName}")
}

@Suppress("UNCHECKED_CAST")
suspend inline fun <reified T> SettingsDataStore.put(value: IPreferenceValue<T>) = when (T::class) {
    String::class ->
        putString(value = value as IPreferenceValue<String>)
    Int::class ->
        putInt(value = value as IPreferenceValue<Int>)
    Boolean::class ->
        putBoolean(value = value as IPreferenceValue<Boolean>)
    Double::class ->
        putDouble(value = value as IPreferenceValue<Double>)
    Float::class ->
        putFloat(value = value as IPreferenceValue<Float>)
    Long::class ->
        putLong(value = value as IPreferenceValue<Long>)
    else ->
        error("No valid preference type ${T::class.simpleName}")
}

@Suppress("UNCHECKED_CAST")
suspend inline fun <reified T> SettingsDataStore.put(pref: IPreference<T>, value: T) = when (T::class) {
    String::class ->
        putString(pref = pref as IPreference<String>, value = value as String)
    Int::class ->
        putInt(pref = pref as IPreference<Int>, value = value as Int)
    Boolean::class ->
        putBoolean(pref = pref as IPreference<Boolean>, value = value as Boolean)
    Double::class ->
        putDouble(pref = pref as IPreference<Double>, value = value as Double)
    Float::class ->
        putFloat(pref = pref as IPreference<Float>, value = value as Float)
    Long::class ->
        putLong(pref = pref as IPreference<Long>, value = value as Long)
    else ->
        error("No valid preference type ${T::class.simpleName}")
}
