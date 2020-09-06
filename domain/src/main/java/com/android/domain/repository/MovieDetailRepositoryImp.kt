package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.MovieDetailResponse
import com.android.domain.api.MovieDetailApi
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?): Single<MovieDetailResponse> {
        return apiService.getMovieDetail(movieId).filterResponse()
    }
}