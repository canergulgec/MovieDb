package com.android.test.repository

import com.android.test.utils.`should be`
import com.caner.data.model.remote.TokenResponse
import com.caner.data.repository.NewTokenRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

class TokenRepositoryTest {

    private val mockRepository = mockk<NewTokenRepository>()

    @Test
    fun `New token flow emits successfully`() = runBlocking {
        // Given
        val userDetails = TokenResponse(true, "1234567")
        coEvery { mockRepository.getNewToken() } returns userDetails

        // When
        val response = mockRepository.getNewToken()

        // Then
        coVerify { mockRepository.getNewToken() }
        response `should be` userDetails
        response.success `should be` userDetails.success
        response.requestToken `should be` userDetails.requestToken
    }

    @Test
    fun `New token flow emits error`() = runBlocking {
        // Given
        val error = Throwable(message = "Test error")
        coEvery { mockRepository.getNewToken() }.throws(error)

        // WHEN
        val exception = assertThrows(Throwable::class.java) {
            runBlocking {
                mockRepository.getNewToken()
            }
        }

        // Then
        exception `should be` error
        exception.message `should be` error.message
    }
}
