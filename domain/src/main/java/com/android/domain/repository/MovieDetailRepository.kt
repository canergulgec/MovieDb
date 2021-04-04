package com.android.domain.repository

import com.android.data.model.MovieDetailModel
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>
}
