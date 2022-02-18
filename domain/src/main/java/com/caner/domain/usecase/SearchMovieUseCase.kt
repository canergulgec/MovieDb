package com.caner.domain.usecase

import com.caner.core.base.BaseUseCase
import com.caner.data.model.MovieModel
import com.caner.core.network.Resource
import com.caner.domain.mapper.MovieMapper
import com.caner.data.repository.SearchRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper
) : BaseUseCase<MovieModel, String?>() {

    override fun buildRequest(params: String?) = flow {
        val response = repository.searchMovie(params)
        if (response is Resource.Success) {
            response.data.apply {
                val sortedList = results.sortedByDescending { it.popularity }
                results = sortedList
                emit(this.mapTo(mapper))
            }
        }
    }
        .onStart { emit(Resource.Loading(true)) }
        .onCompletion { emit(Resource.Loading(false)) }
}
