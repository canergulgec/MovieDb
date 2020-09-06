package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.MoviesResponse
import com.android.domain.api.NowPlayingMoviesApi
import io.reactivex.Single
import javax.inject.Inject

class NowPlayingMoviesRepositoryImp @Inject constructor(
    private val apiService: NowPlayingMoviesApi
) : NowPlayingMoviesRepository {

    override fun getNowPlayingMovies(params: HashMap<String, Any>?): Single<MoviesResponse> {
        return apiService.getNowPlayingMovies(params).filterResponse()
    }
}