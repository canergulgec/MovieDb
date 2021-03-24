package com.caner.common.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.caner.common.PrefKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefStore @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.prefStore: DataStore<Preferences> by preferencesDataStore(name = PrefKeys.PREFERENCE_NAME)

    suspend fun <T> saveData(prefKey: Preferences.Key<T>, data: T) {
        context.prefStore.edit { preferences ->
            preferences[prefKey] = data
        }
    }

    fun <T> getData(prefKey: Preferences.Key<T>): Flow<T?> {
        return context.prefStore.data.map { preferences ->
            preferences[prefKey]
        }
    }
}