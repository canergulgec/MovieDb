package com.caner.moviedb

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MovieDbApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}
