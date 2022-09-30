package com.caner.data.api

import com.caner.domain.model.remote.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String?): MovieResponse
}
