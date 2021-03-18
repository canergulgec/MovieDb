package com.android.test.repository

import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.caner.common.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * This test is written with mockK
 */

class TokenRepositoryTest2 {

    @MockK
    private lateinit var repository: NewTokenRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        //Given
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(userDetails))
            emit(Resource.Loading(false))
        }

        //When
        coEvery { repository.getNewToken() } returns flow
        val getNewToken = repository.getNewToken()


        //Then
        coVerify { repository.getNewToken() }

        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Loading)
            if (index == 1) {
                assert(value is Resource.Success)
                if (value is Resource.Success) {
                    assert(value.data == userDetails)
                    assert(value.data.requestToken == userDetails.requestToken)
                }
            }
            if (index == 2) assert(value is Resource.Loading)
        }
    }
}