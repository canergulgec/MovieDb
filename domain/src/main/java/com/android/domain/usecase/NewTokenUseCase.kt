package com.android.domain.usecase

import com.android.base.UseCase
import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import io.reactivex.Single
import javax.inject.Inject

class NewTokenUseCase @Inject constructor(private val apiRepository: NewTokenRepository) :
    UseCase<TokenResponse, HashMap<String, Any>>() {

    override fun buildUseCaseObservable(params: HashMap<String, Any>?): Single<TokenResponse> {
        return apiRepository.getNewToken()
    }
}