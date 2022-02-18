package com.android.test.repository

import com.android.test.utils.`should be`
import com.caner.data.model.remote.TokenResponse
import com.caner.data.repository.NewTokenRepository
import com.caner.core.network.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TokenRepositoryTest {

    private val mockRepository = mockk<NewTokenRepository>()

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        // Given
        val userDetails = TokenResponse(true, "1234567")
        coEvery { mockRepository.getNewToken() } returns Resource.Success(userDetails)

        // When
        val response = mockRepository.getNewToken()

        // Then
        coVerify { mockRepository.getNewToken() }
        response `should be` Resource.Success(userDetails)
        if (response is Resource.Success) {
            response.data.success `should be` userDetails.success
            response.data.requestToken `should be` userDetails.requestToken
        }
    }

    @Test
    fun `new token flow emits error`() = runBlocking {
        // Given
        val error = Throwable( "Test error")
        coEvery { mockRepository.getNewToken() } returns Resource.Error(error)

        // When
        val response = mockRepository.getNewToken()

        // Then
        coVerify { mockRepository.getNewToken() }
        response `should be` Resource.Error(error)
        if (response is Resource.Error) {
            response.error.message `should be` error.message
        }
    }
}
