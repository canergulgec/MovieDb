package com.android.test

import com.android.moviedb.MovieDbApp

class MovieTestApp : MovieDbApp() {
    companion object {
        const val port = 8080
    }

    override fun getBaseUrl() = "http://localhost:$port"
}
