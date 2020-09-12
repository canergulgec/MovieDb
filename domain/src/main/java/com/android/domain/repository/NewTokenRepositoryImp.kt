package com.android.domain.repository

import com.android.base.Resource
import com.android.base.ext.filterResponse
import com.android.domain.api.NewTokenApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    override fun getNewToken() = flow {
        emit(Resource.Loading)
        val data = apiService.getNewToken()
        emit(data.filterResponse())
    }
}