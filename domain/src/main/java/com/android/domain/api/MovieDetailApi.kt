package com.android.domain.api

import com.android.data.model.remote.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int?): Response<MovieDetailResponse>
}