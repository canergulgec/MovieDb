package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import com.android.domain.api.NowPlayingMoviesApi
import javax.inject.Inject

class NowPlayingMoviesRepositoryImp @Inject constructor(
    private val apiService: NowPlayingMoviesApi
) : NowPlayingMoviesRepository {

    override suspend fun getMovies(params: HashMap<String, Any>?): MoviesResponse {
        return apiService.getNowPlayingMovies(params)
    }
}