package com.android.domain.repository

import com.caner.common.Resource
import com.caner.common.ext.filterResponse
import com.android.domain.api.NewTokenApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewTokenRepositoryImp @Inject constructor(
    private val apiService: NewTokenApi
) : NewTokenRepository {

    @ExperimentalCoroutinesApi
    override fun getNewToken() = flow {
        val data = apiService.getNewToken()
        emit(data.filterResponse())
    }.onStart {
        emit(Resource.Loading)
    }
}