package com.android.domain.usecase

import com.android.base.BaseUseCase
import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.caner.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val apiRepository: MovieDetailRepository
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildRequest(params: Int?): Flow<Resource<MovieDetailModel>> {
        return apiRepository.getMovieDetail(params)
            .onStart { emit(Resource.Loading(true)) }
            .onCompletion { emit(Resource.Loading(false)) }
            .flowOn(Dispatchers.IO)
    }
}