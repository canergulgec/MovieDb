package com.caner.domain.paging

import androidx.paging.PagingSource
import com.caner.domain.pagingsource.MoviesPagingSource
import com.caner.domain.model.remote.MovieResponse
import com.caner.domain.model.remote.MovieResponseItem
import com.caner.domain.repository.MovieRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviePagingSourceTest {

    private val mockRepository = mockk<MovieRepository>()

    private lateinit var pagingSource: MoviesPagingSource

    companion object {
        const val MOVIE_PATH = "now_playing"

        // Given
        private val mockMovieResponseList = listOf(mockk<MovieResponseItem>())
        private val movieResponse = MovieResponse(
            total = 10,
            page = 1,
            results = mockMovieResponseList
        )
    }

    @Before
    fun setUpViewModel() {
        pagingSource = MoviesPagingSource(repository = mockRepository, path = MOVIE_PATH)
    }

    @Test
    fun `Movie paging source Load - Refresh case`() = runTest {
        // When
        coEvery { mockRepository.getMovies(path = any(), page = any()) } returns movieResponse

        val expectedResult = PagingSource.LoadResult.Page(
            data = mockMovieResponseList,
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
        coEvery { mockRepository.getMovies(path = any(), page = any()) } throws error

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
        coEvery { mockRepository.getMovies(path = any(), page = any()) } returns movieResponse

        val expectedResult = PagingSource.LoadResult.Page(
            data = mockMovieResponseList,
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
