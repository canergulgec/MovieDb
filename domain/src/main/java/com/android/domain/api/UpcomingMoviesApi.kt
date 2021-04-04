package com.android.domain.api

import com.android.data.model.remote.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface UpcomingMoviesApi {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@QueryMap params: HashMap<String, Any>?): MoviesResponse
}
