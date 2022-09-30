package com.caner.domain.repository

import com.caner.domain.model.remote.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse
}
