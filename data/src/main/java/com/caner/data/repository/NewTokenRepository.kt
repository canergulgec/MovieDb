package com.caner.data.repository

import com.caner.data.model.remote.TokenResponse
import com.caner.core.network.Resource

interface NewTokenRepository {
    suspend fun getNewToken(): Resource<TokenResponse>
}
