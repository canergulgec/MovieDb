package com.caner.domain.repository

import com.caner.data.model.remote.MovieDetailResponse
import com.caner.core.network.Resource

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): Resource<MovieDetailResponse>
}
