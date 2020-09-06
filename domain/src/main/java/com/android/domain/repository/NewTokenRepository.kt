package com.android.domain.repository

import com.android.data.model.remote.TokenResponse
import io.reactivex.Single

interface NewTokenRepository {
    fun getNewToken(): Single<TokenResponse>
}