package com.caner.domain.repository

import com.caner.domain.api.NewTokenApi
import com.caner.domain.extension.filterResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override fun getNewToken() = flow {
        val data = apiService.getNewToken()
        emit(data.filterResponse())
    }
}
