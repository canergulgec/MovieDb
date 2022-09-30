package com.caner.domain.usecase

import com.caner.domain.extension.buildNetworkRequest
import com.caner.domain.extension.onProgress
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.repository.MovieDetailRepository
import com.caner.domain.extension.mapTo
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
