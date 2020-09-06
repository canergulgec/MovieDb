package com.android.domain.usecase

import com.android.base.UseCase
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.repository.NewSessionRepository
import io.reactivex.Single
import javax.inject.Inject

class NewSessionUseCase @Inject constructor(private val apiRepository: NewSessionRepository) :
    UseCase<NewSessionResponse, HashMap<String, Any>>() {

    override fun buildUseCaseObservable(params: HashMap<String, Any>?): Single<NewSessionResponse> {
        return apiRepository.getNewSession(params)
    }
}