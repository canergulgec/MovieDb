package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import com.android.domain.api.UpcomingMoviesApi
import javax.inject.Inject

class UpcomingMoviesRepositoryImp @Inject constructor(
    private val apiService: UpcomingMoviesApi
) : UpcomingMoviesRepository {

    override suspend fun getMovies(params: HashMap<String, Any>?): MoviesResponse {
        return apiService.getUpcomingMovies(params)
    }
}