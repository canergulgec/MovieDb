package com.caner.data.api

import com.caner.domain.model.remote.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_path}")
    suspend fun getMovies(@Path("movie_path") path: String?, @Query("page") page: Int): MovieResponse
}
