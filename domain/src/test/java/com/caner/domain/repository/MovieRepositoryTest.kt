package com.caner.domain.repository

import com.caner.domain.model.remote.MovieResponse
import com.caner.domain.model.remote.MovieResponseItem
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private val mockRepository = mockk<MovieRepository>()

    @Test
    fun `Movie response returns success case`() = runTest {
        // Given
        val movieItem =
            MovieResponseItem(
                id = 1,
                popularity = 5.0,
                video = false,
                posterPath = "",
                adult = false,
                backdropPath = "",
                originalLanguage = "en",
                originalTitle = "",
                title = "Movie",
                voteAverage = 4.0,
                overview = "",
                releaseDate = ""
            )
        val movieResponse = MovieResponse(
            total = 10,
            page = 1,
            results = listOf(movieItem)
        )

        // When
        coEvery { mockRepository.getMovies(any(), any()) } returns movieResponse

        val request = mockRepository.getMovies(path = "", page = 1)

        // Then
        assertThat(request).isEqualTo(movieResponse)
        assertThat(request.total).isEqualTo(10)
    }

    @Test
    fun `Movie response returns error case`() = runTest {
        // Given
        val error = Throwable(message = "Test error")

        // When
        coEvery { mockRepository.getMovies(any(), any()) } throws error

        val request = assertThrows(Throwable::class.java) {
            runBlocking {
                mockRepository.getMovies(path = "", page = 5)
            }
        }

        // Then
        assertThat(request).isEqualTo(error)
    }
}
