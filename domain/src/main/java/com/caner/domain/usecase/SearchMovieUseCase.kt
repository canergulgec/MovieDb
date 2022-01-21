package com.caner.domain.usecase

import com.caner.core.extension.toModel
import com.caner.data.model.MovieModel
import com.caner.core.qualifier.IoDispatcher
import com.caner.core.network.Resource
import com.caner.data.mapper.MovieMapper
import com.caner.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieModel, String?>() {

    override fun buildRequest(params: String?) = flow {
        when (val response = repository.searchMovie(params)) {
            is Resource.Success -> {
                response.data.apply {
                    val sortedList = results.sortedByDescending { it.popularity }
                    results = sortedList
                    emit(this.toModel(mapper))
                }
            }
            is Resource.Loading -> emit(Resource.Loading(response.status))
            is Resource.Error -> emit(Resource.Error(response.error))
        }
    }
        .onStart { emit(Resource.Loading(true)) }
        .onCompletion { emit(Resource.Loading(false)) }
        .flowOn(dispatcher)
}
