package com.caner.moviedb

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MovieDbApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.apply {
            addPlugin(NetworkFlipperPlugin())
            addPlugin(InspectorFlipperPlugin(this@MovieDbApp, DescriptorMapping.withDefaults()))
            start()
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}
