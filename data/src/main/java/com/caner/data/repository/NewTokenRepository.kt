package com.caner.data.repository

import com.caner.data.model.remote.TokenResponse

interface NewTokenRepository {
    suspend fun getNewToken(): TokenResponse
}
