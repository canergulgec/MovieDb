package com.android.domain.api

import com.android.data.model.remote.MoviesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface UpcomingMoviesApi {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@QueryMap params: HashMap<String, Any>?): Single<Response<MoviesResponse>>
}