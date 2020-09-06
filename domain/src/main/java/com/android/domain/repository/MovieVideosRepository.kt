package com.android.domain.repository

import com.android.data.model.remote.MovieVideosResponse
import io.reactivex.Single

interface MovieVideosRepository {
    fun getMovieVideos(movieId: Int?): Single<MovieVideosResponse>
}