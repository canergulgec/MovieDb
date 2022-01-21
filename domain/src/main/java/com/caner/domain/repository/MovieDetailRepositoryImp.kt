package com.caner.domain.repository

import com.caner.core.extension.toResource
import com.caner.data.model.remote.MovieDetailResponse
import com.caner.core.network.Resource
import com.caner.domain.api.MovieDetailApi
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): Resource<MovieDetailResponse> {
        val data = apiService.getMovieDetail(movieId)
        return data.toResource()
    }
}
