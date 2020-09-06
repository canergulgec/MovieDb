package com.android.domain.api

import com.android.data.model.remote.TokenResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface NewTokenApi {

    @GET("authentication/token/new")
    fun getNewToken(): Single<Response<TokenResponse>>
}