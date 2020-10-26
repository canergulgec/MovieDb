package com.android.domain.repository

import com.android.base.Resource
import com.android.data.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>
}