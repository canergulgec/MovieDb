package com.caner.data.api

import com.caner.data.model.remote.TokenResponse
import retrofit2.http.GET

interface NewTokenApi {

    @GET("authentication/token/new")
    suspend fun getNewToken(): TokenResponse
}
