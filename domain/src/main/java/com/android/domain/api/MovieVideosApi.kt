package com.android.domain.api

import com.android.data.model.remote.MovieVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieVideosApi {

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") id: Int?): Response<MovieVideosResponse>
}