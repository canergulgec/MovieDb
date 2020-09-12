package com.android.domain.usecase

import com.android.base.Resource
import com.android.base.UseCase
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.di.IoDispatcher
import com.android.domain.repository.NewSessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewSessionUseCase @Inject constructor(
    private val apiRepository: NewSessionRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    UseCase<NewSessionResponse, HashMap<String, Any>>() {

    override suspend fun buildRequest(params: HashMap<String, Any>?): Flow<Resource<NewSessionResponse>> {
        return apiRepository.getNewSession(params)
            .flowOn(dispatcher)
    }
}