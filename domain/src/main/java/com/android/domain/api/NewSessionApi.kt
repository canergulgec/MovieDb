package com.android.domain.api

import com.android.data.model.remote.NewSessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewSessionApi {

    @POST("authentication/session/new")
    suspend fun getNewSession(@Body params: HashMap<String, Any>?): Response<NewSessionResponse>
}