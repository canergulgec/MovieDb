package com.caner.domain.repository

import com.caner.data.model.remote.TokenResponse
import com.caner.data.viewstate.Resource
import kotlinx.coroutines.flow.Flow

interface NewTokenRepository {
    fun getNewToken(): Flow<Resource<TokenResponse>>
}
