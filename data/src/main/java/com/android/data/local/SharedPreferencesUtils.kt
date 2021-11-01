package com.android.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesUtils @Inject constructor(val mSharedPreferences: SharedPreferences) {
    fun <T> putData(key: String, data: T) {
        mSharedPreferences.edit().apply {
            when (data) {
                is Int -> putInt(key, data)
                is String -> putString(key, data)
                is Boolean -> putBoolean(key, data)
                is Float -> putFloat(key, data)
                is Long -> putLong(key, data)
            }
        }.apply()
    }

    inline fun <reified T> getData(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is Int -> mSharedPreferences.getInt(key, defaultValue) as T
            is String -> mSharedPreferences.getString(key, defaultValue) as T
            is Boolean -> mSharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> mSharedPreferences.getFloat(key, defaultValue) as T
            is Long -> mSharedPreferences.getLong(key, defaultValue) as T
            else -> throw UnsupportedOperationException()
        }
    }
}
