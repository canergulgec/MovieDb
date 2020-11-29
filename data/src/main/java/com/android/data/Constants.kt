package com.android.data

import androidx.datastore.preferences.core.preferencesKey

object Constants {
    const val API_KEY = "e10ca2c86a2aa68b44a0feb7a5f33b89"
    const val SHARED_PREF_KEY = "SHARED_PREF_KEY"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val MOVIE_ID = "MOVIE_ID"
    const val page = "page"
    const val NOW_PLAYING_MOVIES = 1
    const val UPCOMING_MOVIES = 2
    const val MOVIE_TITLE = "MOVIE_TITLE"
    const val MOVIE_OVERVIEW = "MOVIE_OVERVIEW"
    const val CHANNEL_ID = "NotificationChannelId"
    const val PREFERENCE_NAME = "profile"
    val ACCESS_TOKEN_DATA_STORE = preferencesKey<String>("access_token")
}