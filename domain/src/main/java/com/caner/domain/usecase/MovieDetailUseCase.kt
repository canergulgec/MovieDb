package com.caner.domain.usecase

import com.caner.core.base.BaseUseCase
import com.caner.data.model.MovieDetailModel
import com.caner.core.network.Resource
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildRequest(params: Int?) = flow {
        val response = repository.getMovieDetail(params)
        if (response is Resource.Success) {
            emit(response.data.mapTo(mapper))
        }
    }
        .onStart { emit(Resource.Loading(true)) }
        .onCompletion { emit(Resource.Loading(false)) }
}
