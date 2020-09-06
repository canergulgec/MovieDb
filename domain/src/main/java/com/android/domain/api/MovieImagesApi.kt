package com.android.domain.api

import com.android.data.model.remote.MovieImagesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieImagesApi {

    @GET("movie/{movie_id}/images")
    fun getMovieImages(@Path("movie_id") id: Int?): Single<Response<MovieImagesResponse>>
}