package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.MoviesResponse
import com.android.domain.api.UpcomingMoviesApi
import io.reactivex.Single
import javax.inject.Inject

class UpcomingMoviesRepositoryImp @Inject constructor(
    private val apiService: UpcomingMoviesApi
) : UpcomingMoviesRepository {

    override fun getUpcomingMovies(params: HashMap<String, Any>?): Single<MoviesResponse> {
        return apiService.getUpcomingMovies(params).filterResponse()
    }
}