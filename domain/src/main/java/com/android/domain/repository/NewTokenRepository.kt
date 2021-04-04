package com.android.domain.repository

import com.android.data.model.remote.TokenResponse
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow

interface NewTokenRepository {
    fun getNewToken(): Flow<Resource<TokenResponse>>
}
