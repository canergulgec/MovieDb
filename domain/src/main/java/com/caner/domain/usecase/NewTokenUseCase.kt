package com.caner.domain.usecase

import com.caner.core.base.BaseUseCase
import com.caner.data.model.remote.TokenResponse
import com.caner.core.network.Resource
import com.caner.data.repository.NewTokenRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewTokenUseCase @Inject constructor(
    private val repository: NewTokenRepository
) : BaseUseCase<TokenResponse, HashMap<String, Any>>() {

    override fun buildRequest(params: HashMap<String, Any>?) = flow {
        emit(repository.getNewToken())
    }
        .onStart { emit(Resource.Loading(true)) }
        .onCompletion { emit(Resource.Loading(false)) }
}
