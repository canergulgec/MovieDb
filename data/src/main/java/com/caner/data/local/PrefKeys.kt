package com.caner.data.local

import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    const val SHARED_PREF_KEY = "SHARED_PREF_KEY"
    const val PREFERENCE_NAME = "profile"
    val ACCESS_TOKEN_DATA_STORE = stringPreferencesKey("access_token")
}
