package com.caner.domain.repository

import com.caner.core.extension.toResource
import com.caner.core.network.Resource
import com.caner.data.model.remote.TokenResponse
import com.caner.domain.api.NewTokenApi
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override suspend fun getNewToken(): Resource<TokenResponse> {
        return apiService.getNewToken().toResource()
    }
}
