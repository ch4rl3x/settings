package de.charlex.settings.sharedpreferences.encryption

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences

class EncryptedSettingsImpl internal constructor(
    context: Context,
    name: String,
    mainKeyAlias: String,
    prefKeyEncryptionScheme: EncryptedSharedPreferences.PrefKeyEncryptionScheme,
    prefValueEncryptionScheme: EncryptedSharedPreferences.PrefValueEncryptionScheme
) : EncryptedSettings {

    val settings: SharedPreferences = EncryptedSharedPreferences.create(
        name,
        mainKeyAlias,
        context,
        prefKeyEncryptionScheme,
        prefValueEncryptionScheme
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(pref: IEncryptedSharedPreference<T>): T {
        return settings.all[pref.preferenceKey] as T ?: pref.defaultValue
    }

    override fun <T> put(pref: IEncryptedSharedPreference<T>, value: T) {
        val edit = settings.edit()
        val name = pref.preferenceKey
        when (value) {
            is String -> edit.putString(name, value)
            is Boolean -> edit.putBoolean(name, value)
            is Int -> edit.putInt(name, value)
            is Long -> edit.putLong(name, value)
            is Float -> edit.putFloat(name, value)
            is Set<*> -> {
                val stringSet = value.filterIsInstance<String>().toSet()
                if (stringSet.size != value.size) {
                    error(
                        "Cannot save preference with key: ${pref.preferenceKey}, value: ${value}\".\n" +
                            "Only String sets can be saved and this set seems to contain other instances."
                    )
                }
                edit.putStringSet(name, stringSet)
            }
            else -> error("Cannot save preference with key: ${pref.preferenceKey}, value: $value")
        }
        edit.apply()
    }

    override fun <T : Enum<T>, U> put(pref: IEncryptedEnumSharedPreference<T, U>, value: T) {
        val edit = settings.edit()
        when (val enumValue = pref.keyProperty.call(value)) {
            is String -> edit.putString(pref.preferenceKey, enumValue as String)
            is Int -> edit.putInt(pref.preferenceKey, enumValue as Int)
            is Float -> edit.putFloat(pref.preferenceKey, enumValue as Float)
            is Long -> edit.putLong(pref.preferenceKey, enumValue as Long)
            is Boolean -> edit.putBoolean(pref.preferenceKey, enumValue as Boolean)
            else -> error("No valid enum key value: $enumValue")
        }
        edit.apply()
    }

    override fun <T> remove(pref: IEncryptedSharedPreference<T>) {
        val edit = settings.edit()
        edit.remove(pref.preferenceKey)
        edit.apply()
    }

    override fun <T : Enum<T>, U> remove(pref: IEncryptedEnumSharedPreference<T, U>) {
        val edit = settings.edit()
        edit.remove(pref.preferenceKey)
        edit.apply()
    }

    override fun clear() {
        val edit = settings.edit()
        edit.clear()
        edit.apply()
    }
}
