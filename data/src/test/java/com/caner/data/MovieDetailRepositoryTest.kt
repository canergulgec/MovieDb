package com.caner.data

import com.caner.data.api.MovieDetailApi
import com.caner.data.repository.MovieDetailRepositoryImp
import com.caner.domain.model.remote.MovieDetailResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailRepositoryTest {
    private val mockApiService = mockk<MovieDetailApi>()

    private lateinit var movieDetailRepository: MovieDetailRepositoryImp

    @Before
    fun setup() {
        movieDetailRepository = MovieDetailRepositoryImp(mockApiService)
    }

    @Test
    fun `Should return successful response when asked for movie detail`() = runTest {
        // Given
        val movieID = 1
        val mockMovieDetailResponse = mockk<MovieDetailResponse> {
            every { id } returns movieID
        }
        coEvery { mockApiService.getMovieDetail(any()) } returns mockMovieDetailResponse

        // When
        val response = movieDetailRepository.getMovieDetail(movieID)

        // Then
        assertThat(response).isEqualTo(mockMovieDetailResponse)
        assertThat(response.id).isEqualTo(movieID)

        // Verify
        coVerify(exactly = 1) { movieDetailRepository.getMovieDetail(any()) }
    }

    @Test
    fun `Should return error when asked for movie detail`() = runTest {
        val movieID = 1
        val error = Throwable(message = "Test error")
        coEvery { mockApiService.getMovieDetail(any()) } throws (error)

        // When
        val response = runCatching {
            movieDetailRepository.getMovieDetail(movieID)
        }.onFailure {
            assertThat(it).isInstanceOf(Throwable::class.java)
        }

        // Then
        assertThat(response.isFailure).isTrue()

        // Verify
        coVerify(exactly = 1) { movieDetailRepository.getMovieDetail(any()) }
    }
}
