package com.android.domain.repository

import com.android.data.model.remote.MovieDetailResponse
import io.reactivex.Single

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Single<MovieDetailResponse>
}