package com.caner.common.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import com.caner.common.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreUtils @Inject constructor(@ApplicationContext val context: Context) {

    private var dataStore: DataStore<Preferences> =
        context.createDataStore(name = Constants.PREFERENCE_NAME)

    suspend fun <T> saveData(prefKey: Preferences.Key<T>, data: T) {
        dataStore.edit { preferences ->
            preferences[prefKey] = data
        }
    }

    fun <T> getData(prefKey: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.map { preferences ->
            preferences[prefKey]
        }
    }
}