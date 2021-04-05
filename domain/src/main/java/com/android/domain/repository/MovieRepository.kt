package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse

interface MovieRepository {
    suspend fun getNowPlayingMovies(params: HashMap<String, Any>?): MoviesResponse

    suspend fun getUpcomingMovies(params: HashMap<String, Any>?): MoviesResponse
}
