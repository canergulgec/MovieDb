package com.caner.data

import androidx.paging.PagingSource
import com.caner.data.api.MovieApi
import com.caner.data.pagingsource.MoviesPagingSource
import com.caner.domain.model.remote.MovieResponse
import com.caner.domain.model.remote.MovieResponseItem
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private val mockApi = mockk<MovieApi>()

    private lateinit var pagingSource: MoviesPagingSource

    companion object {
        const val MOVIE_PATH = "now_playing"

        // Given
        private val movieItem =
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
        private val movieResponse = MovieResponse(
            total = 10,
            page = 1,
            results = listOf(movieItem)
        )
    }

    @Before
    fun setUpViewModel() {
        pagingSource = MoviesPagingSource(service = mockApi, path = MOVIE_PATH)
    }

    @Test
    fun `Movie paging source Load - Refresh case`() = runTest {
        // When
        coEvery { mockApi.getMovies(path = any(), page = any()) } returns movieResponse

        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(movieItem),
            prevKey = null,
            nextKey = 2
        )

        // Then
        assertThat(expectedResult).isEqualTo(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `Movie paging source Load - Error case`() = runTest {
        val error = RuntimeException("404", Throwable())
        coEvery { mockApi.getMovies(path = any(), page = any()) } throws error

        val expectedResult = PagingSource.LoadResult.Error<Int, MovieResponseItem>(error)

        // Then
        assertThat(expectedResult).isEqualTo(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `Movie paging source Load - Append case`() = runTest {
        // When
        coEvery { mockApi.getMovies(path = any(), page = any()) } returns movieResponse

        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(movieItem),
            prevKey = 1,
            nextKey = 3
        )

        // Then
        assertThat(expectedResult).isEqualTo(
            pagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 2,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
