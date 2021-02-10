package de.charlex.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {

    fun getString(pref: IPreference<String>): Flow<String>
    fun getInt(pref: IPreference<Int>): Flow<Int>
    fun getFloat(pref: IPreference<Float>): Flow<Float>
    fun getBoolean(pref: IPreference<Boolean>): Flow<Boolean>
    fun getLong(pref: IPreference<Long>): Flow<Long>

    suspend fun putString(value: IPreferenceValue<String>)
    suspend fun putInt(value: IPreferenceValue<Int>)
    suspend fun putFloat(value: IPreferenceValue<Float>)
    suspend fun putBoolean(value: IPreferenceValue<Boolean>)
    suspend fun putLong(value: IPreferenceValue<Long>)
    suspend fun putString(pref: IPreference<String>, value: String)
    suspend fun putInt(pref: IPreference<Int>, value: Int)
    suspend fun putFloat(pref: IPreference<Float>, value: Float)
    suspend fun putBoolean(pref: IPreference<Boolean>, value: Boolean)
    suspend fun putLong(pref: IPreference<Long>, value: Long)

    companion object {
        fun create(context: Context): SettingsDataStore {
            return SettingsDataStoreImpl(context)
        }
        fun createInMemory(): SettingsDataStore {
            return SettingsDataStoreInMemoryImpl()
        }
    }
}
