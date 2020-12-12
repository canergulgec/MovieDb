package com.android.domain.repository

import com.caner.common.Mapper
import com.caner.common.Resource
import com.android.base.ext.filterMapperResponse
import com.android.data.model.MovieDetailModel
import com.android.data.model.remote.MovieDetailResponse
import com.android.domain.api.MovieDetailApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi,
    private val movieDetailMapper: Mapper<MovieDetailResponse, MovieDetailModel>,
) : MovieDetailRepository {

    @ExperimentalCoroutinesApi
    override fun getMovieDetail(movieId: Int?) = flow {
        val data = apiService.getMovieDetail(movieId)
        emit(data.filterMapperResponse(movieDetailMapper))
    }.onStart {
        emit(Resource.Loading)
    }
}