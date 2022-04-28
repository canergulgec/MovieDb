package com.caner.domain.usecase

import com.caner.core.extension.onProgress
import com.caner.core.network.Resource
import com.caner.domain.mapper.MovieMapper
import com.caner.data.repository.SearchRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: MovieMapper
) {

    fun searchMovie(query: String) = flow {
        val response = repository.searchMovie(query)
        response.apply {
            val sortedList = results.sortedByDescending { it.popularity }
            results = sortedList
            emit(this.mapTo(mapper))
        }
    }
        .catch { error ->
            emit(Resource.Error(Throwable(message = error.message)))
            emit(Resource.Loading(false))
        }
        .onProgress()
        .flowOn(Dispatchers.IO)
}
