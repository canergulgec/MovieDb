package com.android.domain.repository

import com.android.base.Resource
import com.android.data.model.remote.TokenResponse
import kotlinx.coroutines.flow.Flow

interface NewTokenRepository {
    fun getNewToken(): Flow<Resource<TokenResponse>>
}