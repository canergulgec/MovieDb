package com.caner.domain.repository

import com.caner.data.model.MovieDetailModel
import com.caner.data.viewstate.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>
}
