package com.caner.data.repository

import com.caner.data.api.MovieDetailApi
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?) =
        apiService.getMovieDetail(movieId)
}
