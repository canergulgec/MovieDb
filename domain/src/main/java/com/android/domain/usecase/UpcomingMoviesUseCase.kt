package com.android.domain.usecase

import com.android.base.UseCase
import com.android.data.model.remote.MoviesResponse
import com.android.domain.repository.UpcomingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class UpcomingMoviesUseCase @Inject constructor(private val apiRepository: UpcomingMoviesRepository) :
    UseCase<MoviesResponse, HashMap<String, Any>>() {

    override fun buildUseCaseObservable(params: HashMap<String, Any>?): Single<MoviesResponse> {
        return apiRepository.getUpcomingMovies(params)
    }
}