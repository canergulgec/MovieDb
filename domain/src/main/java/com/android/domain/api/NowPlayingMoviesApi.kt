package com.android.domain.api

import com.android.data.model.remote.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NowPlayingMoviesApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@QueryMap params: HashMap<String, Any>?): MoviesResponse
}