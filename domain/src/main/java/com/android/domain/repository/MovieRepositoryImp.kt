package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import com.android.domain.api.NowPlayingMoviesApi
import com.android.domain.api.UpcomingMoviesApi
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val nowPlayingMoviesApi: NowPlayingMoviesApi,
    private val upcomingMoviesApi: UpcomingMoviesApi,
) : MovieRepository {

    override suspend fun getNowPlayingMovies(params: HashMap<String, Any>?): MoviesResponse {
        return nowPlayingMoviesApi.getNowPlayingMovies(params)
    }

    override suspend fun getUpcomingMovies(params: HashMap<String, Any>?): MoviesResponse {
        return upcomingMoviesApi.getUpcomingMovies(params)
    }
}
