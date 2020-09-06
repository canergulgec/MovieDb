package com.android.domain.repository

import com.android.data.model.remote.MovieImagesResponse
import io.reactivex.Single

interface MovieImagesRepository {
    fun getMovieImages(movieId: Int?): Single<MovieImagesResponse>
}