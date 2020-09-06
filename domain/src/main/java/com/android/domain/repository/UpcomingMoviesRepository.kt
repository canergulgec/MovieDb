package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import io.reactivex.Single

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(params: HashMap<String, Any>?): Single<MoviesResponse>
}