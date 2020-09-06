package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.MovieImagesResponse
import com.android.domain.api.MovieImagesApi
import io.reactivex.Single
import javax.inject.Inject

class MovieImagesRepositoryImp @Inject constructor(
    private val apiService: MovieImagesApi
) : MovieImagesRepository {

    override fun getMovieImages(movieId: Int?): Single<MovieImagesResponse> {
        return apiService.getMovieImages(movieId).filterResponse()
    }
}