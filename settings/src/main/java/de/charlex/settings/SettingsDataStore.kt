package de.charlex.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

@Deprecated("Still WIP", level = DeprecationLevel.WARNING)
interface SettingsDataStore {
    val dataStore: DataStore<Preferences>

    fun createStringFlow(pref: IPreference<String>): Flow<String>
    fun createIntFlow(pref: IPreference<Int>): Flow<Int>
    fun createFloatFlow(pref: IPreference<Float>): Flow<Float>
    fun createBooleanFlow(pref: IPreference<Boolean>): Flow<Boolean>
    fun createLongFlow(pref: IPreference<Long>): Flow<Long>
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
}
