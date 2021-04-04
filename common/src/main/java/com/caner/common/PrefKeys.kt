package com.caner.common

import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    const val PREFERENCE_NAME = "profile"
    val ACCESS_TOKEN_DATA_STORE = stringPreferencesKey("access_token")
}
