package com.caner.data.repository

import com.caner.data.api.NewTokenApi
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override suspend fun getNewToken() =
        apiService.getNewToken()
}
