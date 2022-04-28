package com.caner.domain.usecase

import com.caner.core.extension.onProgress
import com.caner.core.network.Resource
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {

    fun getMovieDetail(movieId: Int?) = flow {
        val response = repository.getMovieDetail(movieId)
        emit(response.mapTo(mapper))
    }
        .catch { error ->
            emit(Resource.Error(Throwable(message = error.message)))
            emit(Resource.Loading(false))
        }
        .onProgress()
        .flowOn(Dispatchers.IO)
}
