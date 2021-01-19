package com.android.test

import com.android.moviedb.MovieDbApp

class MovieTestApp : MovieDbApp() {

    override fun getBaseUrl() = "http://localhost:8080"
}