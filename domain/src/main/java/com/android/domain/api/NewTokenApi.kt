package com.android.domain.api

import com.android.data.model.remote.TokenResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewTokenApi {

    @GET("authentication/token/new")
    suspend fun getNewToken(): Response<TokenResponse>
}
