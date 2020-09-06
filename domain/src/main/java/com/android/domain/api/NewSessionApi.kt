package com.android.domain.api

import com.android.data.model.remote.NewSessionResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewSessionApi {

    @POST("authentication/session/new")
    fun getNewSession(@Body params: HashMap<String, Any>?): Single<Response<NewSessionResponse>>
}