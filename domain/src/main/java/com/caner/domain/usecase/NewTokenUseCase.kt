package com.caner.domain.usecase

import com.caner.data.model.remote.TokenResponse
import com.caner.core.qualifier.IoDispatcher
import com.caner.core.network.Resource
import com.caner.domain.repository.NewTokenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewTokenUseCase @Inject constructor(
    private val repository: NewTokenRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<TokenResponse, HashMap<String, Any>>() {

    override fun buildRequest(params: HashMap<String, Any>?) = flow {
        emit(repository.getNewToken())
    }
        .onStart { emit(Resource.Loading(true)) }
        .onCompletion { emit(Resource.Loading(false)) }
        .flowOn(dispatcher)
}
