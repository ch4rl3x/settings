package de.charlex.settings.datastore

import androidx.datastore.preferences.core.Preferences
import kotlin.reflect.KProperty

interface IDataStorePreference<T> {
    val preferenceKey: Preferences.Key<T>
    val defaultValue: T
}

interface IDataStoreEnumPreference<T, U> {
    val preferenceKey: Preferences.Key<U>
    val defaultValue: T
    val keyProperty: KProperty<U>
}
