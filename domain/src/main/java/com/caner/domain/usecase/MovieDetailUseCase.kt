package com.caner.domain.usecase

import com.caner.core.base.BaseUseCase
import com.caner.core.extension.onProgress
import com.caner.data.model.MovieDetailModel
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.data.repository.MovieDetailRepository
import com.caner.domain.mapper.mapTo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildResponse(params: Int?) = flow {
        val response = repository.getMovieDetail(params)
        emit(response.mapTo(mapper))
    }
        .onProgress()
}
