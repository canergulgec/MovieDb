package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.TokenResponse
import com.android.domain.api.NewTokenApi
import io.reactivex.Single
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override fun getNewToken(): Single<TokenResponse> {
        return apiService.getNewToken().filterResponse()
    }
}