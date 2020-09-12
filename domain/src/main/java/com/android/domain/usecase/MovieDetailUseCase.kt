package com.android.domain.usecase

import com.android.base.Resource
import com.android.base.UseCase
import com.android.data.model.MovieDetailModel
import com.android.domain.di.IoDispatcher
import com.android.domain.repository.MovieDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val apiRepository: MovieDetailRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    UseCase<MovieDetailModel, Int?>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<MovieDetailModel>> {
        return apiRepository.getMovieDetail(params)
            .flowOn(dispatcher)
    }
}