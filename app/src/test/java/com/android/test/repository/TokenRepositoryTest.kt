package com.android.test.repository

import com.android.test.utils.`should be`
import com.caner.core.network.ApiError
import com.caner.data.model.remote.TokenResponse
import com.caner.data.repository.NewTokenRepository
import com.caner.core.network.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TokenRepositoryTest {

    @MockK
    private lateinit var repository: NewTokenRepository

    @Before
    fun setUp() =
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        // Given
        val userDetails = TokenResponse(true, "1234567")
        coEvery { repository.getNewToken() } returns Resource.Success(userDetails)

        // When
        val response = repository.getNewToken()

        // Then
        coVerify { repository.getNewToken() }
        response `should be` Resource.Success(userDetails)
        if (response is Resource.Success) {
            response.data.success `should be` userDetails.success
            response.data.requestToken `should be` userDetails.requestToken
        }
    }

    @Test
    fun `new token flow emits error`() = runBlocking {
        // Given
        val error = ApiError(1, "Error happened")
        coEvery { repository.getNewToken() } returns Resource.Error(error)

        // When
        val response = repository.getNewToken()

        // Then
        coVerify { repository.getNewToken() }
        response `should be` Resource.Error(error)
        if (response is Resource.Error) {
            response.error.code `should be` error.code
            response.error.message `should be` error.message
        }
    }
}
