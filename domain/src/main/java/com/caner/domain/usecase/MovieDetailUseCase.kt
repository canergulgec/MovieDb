package com.caner.domain.usecase

import com.caner.core.extension.buildNetworkRequest
import com.caner.core.extension.onProgress
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {

    fun getMovieDetail(movieId: Int?) = flow {
        val response = repository.getMovieDetail(movieId)
        emit(response.mapTo(mapper))
    }
        .onProgress()
        .buildNetworkRequest()
}
