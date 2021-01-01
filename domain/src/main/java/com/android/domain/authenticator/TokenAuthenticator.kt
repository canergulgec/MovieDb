package com.android.domain.authenticator

import com.caner.common.utils.DataStoreUtils
import com.caner.common.Resource
import com.caner.common.utils.SharedPreferencesUtils
import com.caner.common.Constants
import com.android.domain.usecase.NewTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.*

@ExperimentalCoroutinesApi
class TokenAuthenticator constructor(
    private val prefUtils: SharedPreferencesUtils,
    private val useCase: NewTokenUseCase,
    private val storeUtils: DataStoreUtils
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
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
                if (it is Resource.Success) {
                    newToken = it.data.requestToken
                    prefUtils.putData(Constants.ACCESS_TOKEN, newToken)
                    //dataStore
                    storeUtils.saveData(Constants.ACCESS_TOKEN_DATA_STORE, newToken)
                }
            }
        }

        return newToken
    }
}

