package com.android.domain.api

import com.android.data.model.remote.MovieDetailResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApi {

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int?): Single<Response<MovieDetailResponse>>
}