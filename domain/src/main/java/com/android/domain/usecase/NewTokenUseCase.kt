package com.android.domain.usecase

import com.android.base.Resource
import com.android.base.UseCase
import com.android.data.model.remote.TokenResponse
import com.android.domain.di.IoDispatcher
import com.android.domain.repository.NewTokenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewTokenUseCase @Inject constructor(
    private val apiRepository: NewTokenRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<TokenResponse, HashMap<String, Any>>() {

    override suspend fun buildRequest(params: HashMap<String, Any>?): Flow<Resource<TokenResponse>> {
        return apiRepository.getNewToken()
            .flowOn(dispatcher)
    }
}