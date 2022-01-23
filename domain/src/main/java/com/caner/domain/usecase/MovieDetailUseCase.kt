package com.caner.domain.usecase

import com.caner.data.model.MovieDetailModel
import com.caner.core.network.Resource
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.core.qualifier.IoDispatcher
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildRequest(params: Int?) = flow {
        when (val response = repository.getMovieDetail(params)) {
            is Resource.Success -> emit(response.data.mapTo(mapper))
            is Resource.Loading -> emit(Resource.Loading(response.status))
            is Resource.Error -> emit(Resource.Error(response.error))
        }
    }
        .flowOn(dispatcher)
}
