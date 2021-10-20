package com.android.domain.api

import com.android.data.model.remote.MovieResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@QueryMap params: HashMap<String, Any>?): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@QueryMap params: HashMap<String, Any>?): MovieResponse
}
