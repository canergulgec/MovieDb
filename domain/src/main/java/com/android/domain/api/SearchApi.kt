package com.android.domain.api

import com.android.data.model.remote.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String?): Response<MoviesResponse>
}