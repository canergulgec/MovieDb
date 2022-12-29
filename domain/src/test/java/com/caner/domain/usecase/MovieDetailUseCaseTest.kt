package com.caner.domain.usecase

import app.cash.turbine.test
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.utils.state.Resource
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.model.remote.MovieDetailResponse
import com.caner.domain.repository.MovieDetailRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    private val mockMovieDetailRepository = mockk<MovieDetailRepository>()
    private val mockMovieDetailMapper = mockk<MovieDetailMapper>()

    private lateinit var movieDetailUseCase: MovieDetailUseCase

    @Before
    fun setup() {
        movieDetailUseCase = MovieDetailUseCase(repository = mockMovieDetailRepository, mapper = mockMovieDetailMapper)
    }

    @Test
    fun `Should return successful response when asked for movie detail`() = runTest {
        // Given
        val id = 1
        val mockMovieDetailResponse = mockk<MovieDetailResponse>()
        val mockMovieDetailModel = mockk<MovieDetailModel> {
            every { movieId } returns id
        }

        coEvery { mockMovieDetailRepository.getMovieDetail(any()) } returns mockMovieDetailResponse
        every { mockMovieDetailMapper.to(mockMovieDetailResponse) } returns mockMovieDetailModel

        movieDetailUseCase.getMovieDetail(any()).test {
            assertThat(awaitItem()).isEqualTo(Resource.Loading(true))
            assertThat((awaitItem() as Resource.Success).data.movieId).isEqualTo(id)
            assertThat(awaitItem()).isEqualTo(Resource.Loading(false))
            awaitComplete()
        }
    }

    @Test
    fun `Should return error when asked for movie detail`() = runTest {
        // Given
        val error = Throwable("error")

        coEvery { mockMovieDetailRepository.getMovieDetail(any()) } throws error

        movieDetailUseCase.getMovieDetail(any()).test {
            assertThat(awaitItem()).isEqualTo(Resource.Loading(true))
            assertThat((awaitItem() as Resource.Error).error.message).isEqualTo(error.message)
            assertThat(awaitItem()).isEqualTo(Resource.Loading(false))
            awaitComplete()
        }
    }
}
