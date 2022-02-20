package com.caner.data.repository

import com.caner.data.model.remote.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse
}
