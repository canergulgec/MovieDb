package com.android.domain.usecase

import com.android.base.BaseUseCase
import com.android.data.model.remote.TokenResponse
import com.android.domain.qualifier.IoDispatcher
import com.android.domain.repository.NewTokenRepository
import com.caner.common.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewTokenUseCase @Inject constructor(
    private val apiRepository: NewTokenRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<TokenResponse, HashMap<String, Any>>() {

    override fun buildRequest(params: HashMap<String, Any>?): Flow<Resource<TokenResponse>> {
        return apiRepository.getNewToken()
            .onStart { emit(Resource.Loading(true)) }
            .onCompletion { emit(Resource.Loading(false)) }
            .flowOn(dispatcher)
    }
}