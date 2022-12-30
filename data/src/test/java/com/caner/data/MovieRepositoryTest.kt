package com.caner.data

import com.caner.data.api.MovieApi
import com.caner.data.repository.MovieRepositoryImp
import com.caner.domain.model.remote.MovieResponse
import com.caner.domain.model.remote.MovieResponseItem
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {
    private val mockApiService = mockk<MovieApi>()

    private lateinit var movieRepository: MovieRepositoryImp

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImp(mockApiService)
    }

    @Test
    fun `Should return successful response when asked for movies`() = runTest {
        // Given
        val totalResultNumber = 10
        val mockMovieItem = mockk<MovieResponseItem>()
        val movieResponse = MovieResponse(
            total = totalResultNumber,
            page = 1,
            results = listOf(mockMovieItem)
        )

        // When
        coEvery { mockApiService.getMovies(path = any(), page = any()) } returns movieResponse

        val response = movieRepository.getMovies(path = "", page = 1)

        // Then
        assertThat(response).isEqualTo(movieResponse)
        assertThat(response.total).isEqualTo(totalResultNumber)
    }

    @Test
    fun `Movie response returns error case`() = runTest {
        // Given
        val error = Throwable(message = "Test error")

        // When
        coEvery { mockApiService.getMovies(any(), any()) } throws error

        // When
        val response = runCatching {
            movieRepository.getMovies(path = "", page = 1)
        }.onFailure {
            assertThat(it).isInstanceOf(Throwable::class.java)
        }

        // Then
        assertThat(response.isFailure).isTrue()

        // Verify
        coVerify(exactly = 1) { movieRepository.getMovies(any(), any()) }
    }
}
