package com.android.test.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

fun dispatcher() = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(FileReader.readTestResourceFile("movie_response.json"))
    }
}

fun dispatcherWithCustomBody() = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        with(request.path) {
            val response = MockResponse().setResponseCode(200)
            return when {
                this?.contains("/images") == true -> response
                    .setBody(FileReader.readTestResourceFile("movie_images_response.json"))
                this?.contains("/videos") == true -> response
                    .setBody(FileReader.readTestResourceFile("movie_videos_response.json"))
                this?.contains("/now_playing") == true || this?.contains("/upcoming") == true -> response
                    .setBody(FileReader.readTestResourceFile("movie_response.json"))
                else -> response
                    .setBody(FileReader.readTestResourceFile("movie_detail_response.json"))
            }
        }
    }
}