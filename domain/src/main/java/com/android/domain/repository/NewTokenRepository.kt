package com.android.domain.repository

import com.caner.common.Resource
import com.android.data.model.remote.TokenResponse
import kotlinx.coroutines.flow.Flow

interface NewTokenRepository {
    fun getNewToken(): Flow<Resource<TokenResponse>>
}