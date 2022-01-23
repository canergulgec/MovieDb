package com.caner.data.repository

import com.caner.core.extension.toResource
import com.caner.core.network.Resource
import com.caner.data.api.NewTokenApi
import com.caner.data.model.remote.TokenResponse
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override suspend fun getNewToken(): Resource<TokenResponse> {
        return apiService.getNewToken().toResource()
    }
}
