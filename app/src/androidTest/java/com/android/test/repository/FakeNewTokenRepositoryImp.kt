package com.android.test.repository

import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.android.test.utils.TestDataGenerator
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeNewTokenRepositoryImp @Inject constructor() : NewTokenRepository {

    override fun getNewToken(): Flow<Resource<TokenResponse>> {
        return flow { emit(Resource.Success(TestDataGenerator.generateTokenResponse())) }
    }
}
