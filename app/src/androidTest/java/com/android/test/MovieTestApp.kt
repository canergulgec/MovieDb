package com.android.test

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.android.moviedb.MovieDbApp
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MovieTestApp : MovieDbApp() {
    private val mockWebServer = MockWebServer()

    override fun onCreate() {
        super.onCreate()

        mockWebServer.start(8080)

        val resource: IdlingResource =
            OkHttp3IdlingResource.create("OkHttp", OkHttpProvider.getOkHttpClient())
        IdlingRegistry.getInstance().register(resource)
        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200)
            }
        }

        mockWebServer.setDispatcher(dispatcher)
    }

    override fun getBaseUrl() = "http://localhost:8080"

    override fun onTerminate() {
        super.onTerminate()
        mockWebServer.shutdown()
    }
}