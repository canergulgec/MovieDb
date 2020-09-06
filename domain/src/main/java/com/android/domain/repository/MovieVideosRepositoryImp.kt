package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.MovieVideosResponse
import com.android.domain.api.MovieVideosApi
import io.reactivex.Single
import javax.inject.Inject

class MovieVideosRepositoryImp @Inject constructor(
    private val apiService: MovieVideosApi
) : MovieVideosRepository {

    override fun getMovieVideos(movieId: Int?): Single<MovieVideosResponse> {
        return apiService.getMovieVideos(movieId).filterResponse()
    }
}