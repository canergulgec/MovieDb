package com.caner.common

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val SHARED_PREF_KEY = "SHARED_PREF_KEY"
    const val MOVIE_ID = "MOVIE_ID"
    const val page = "page"
    const val NOW_PLAYING_MOVIES = 1
    const val UPCOMING_MOVIES = 2
    const val MOVIE_TITLE = "MOVIE_TITLE"
    const val MOVIE_OVERVIEW = "MOVIE_OVERVIEW"
    const val CHANNEL_ID = "NotificationChannelId"
    const val PREFERENCE_NAME = "profile"
    val ACCESS_TOKEN_DATA_STORE = stringPreferencesKey("access_token")
}