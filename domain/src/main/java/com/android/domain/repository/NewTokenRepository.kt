package com.android.domain.repository

import com.android.data.model.remote.TokenResponse
import com.android.domain.viewstate.Resource
import kotlinx.coroutines.flow.Flow

interface NewTokenRepository {
    fun getNewToken(): Flow<Resource<TokenResponse>>
}
