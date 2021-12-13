package com.caner.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefStore @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.prefStore: DataStore<Preferences> by preferencesDataStore(
        name = PrefKeys.PREFERENCE_NAME
    )

    suspend fun <T> saveData(prefKey: Preferences.Key<T>, data: T) {
        context.prefStore.edit { preferences ->
            preferences[prefKey] = data
        }
    }

    suspend fun <T> getData(prefKey: Preferences.Key<T>): T? {
        return context.prefStore.data.map { preferences ->
            preferences[prefKey]
        }.firstOrNull()
    }
}
