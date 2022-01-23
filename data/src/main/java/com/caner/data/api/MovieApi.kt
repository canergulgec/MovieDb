package com.caner.data.api

import com.caner.data.model.remote.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("movie/{movie_path}")
    suspend fun getMovies(
        @Path("movie_path") path: String?,
        @QueryMap params: HashMap<String, Any>?
    ): MovieResponse
}
