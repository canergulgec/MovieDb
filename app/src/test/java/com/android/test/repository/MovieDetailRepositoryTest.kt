package com.android.test.repository

import com.android.test.utils.`should be`
import com.caner.data.model.MovieDetailModel
import com.caner.data.model.remote.MovieDetailResponse
import com.caner.data.repository.MovieDetailRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Test

class MovieDetailRepositoryTest {

    private val mockRepository = mockk<MovieDetailRepository>()

    @Test
    fun `Movie detail flow emits successfully`() = runBlocking {
        // Given
        val movieDetail = MovieDetailResponse(id = 1)
        coEvery { mockRepository.getMovieDetail(any()) } returns movieDetail

        // When
        val response = mockRepository.getMovieDetail(movieId = 1)

        // Then
        coVerify { mockRepository.getMovieDetail(movieId = 1) }
        response `should be` movieDetail
        response.id `should be` movieDetail.id
    }

    @Test
    fun `Movie detail flow emits error`() = runBlocking {
        // Given
        val error = Throwable(message = "Test error")
        coEvery { mockRepository.getMovieDetail(any()) }.throws(error)

        // WHEN
        val exception = assertThrows(Throwable::class.java) {
            runBlocking {
                mockRepository.getMovieDetail(movieId = 1)
            }
        }

        // Then
        exception `should be` error
        exception.message `should be` error.message
    }
}