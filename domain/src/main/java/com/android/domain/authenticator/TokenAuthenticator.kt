package com.android.domain.authenticator

import com.android.base.Resource
import com.android.base.SharedPreferencesUtils
import com.android.data.Constants
import com.android.domain.usecase.NewTokenUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.*

class TokenAuthenticator constructor(
    private val prefUtils: SharedPreferencesUtils,
    private val useCase: NewTokenUseCase
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // This is a synchronous call
        val updatedToken = getNewToken()
        val formBody: RequestBody = FormBody.Builder()
            .add("request_token", updatedToken)
            .build()

        return response.request.newBuilder()
            .post(formBody)
            // .method(response.request.method, response.request.body) // Optional
            .build()
    }

    private fun getNewToken(): String {
        var newToken = ""
        runBlocking {
            val accessToken = useCase.execute()
            accessToken.collect {
                when (it) {
                    is Resource.Success -> {
                        newToken = it.data.requestToken
                        prefUtils.putData(Constants.ACCESS_TOKEN, newToken)
                    }
                    is Resource.Error -> {
                    }
                }
            }
        }

        return newToken
    }
}

