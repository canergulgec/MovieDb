package com.android.domain.usecase

import com.android.base.UseCase
import com.android.data.mapper.MovieDetailMapper
import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val apiRepository: MovieDetailRepository,
    private val movieDetailMapper: MovieDetailMapper
) :
    UseCase<MovieDetailModel, Int?>() {

    override fun buildUseCaseObservable(params: Int?): Single<MovieDetailModel> {
        return apiRepository.getMovieDetail(params)
            .map {
                movieDetailMapper.to(it)
            }
    }
}