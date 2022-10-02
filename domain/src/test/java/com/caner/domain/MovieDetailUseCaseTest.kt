package com.caner.domain

import com.caner.domain.utils.state.Resource
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.usecase.MovieDetailUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    private val mockUseCase = mockk<MovieDetailUseCase>()

    @Test
    fun `Get movie detail from useCase should return success case`() = runTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.getMovieDetail(any()) } returns flow

        // Then
        val job = launch {
            mockUseCase.getMovieDetail(any()).collectIndexed { index, value ->
                when (index) {
                    0 -> assertThat(value).isEqualTo(Resource.Loading(true))
                    1 -> assertThat(value).isEqualTo(Resource.Success(detailModel))
                    2 -> assertThat(value).isEqualTo(Resource.Loading(false))
                }
            }
        }

        // When
        mockUseCase.getMovieDetail(any())
        coVerify { mockUseCase.getMovieDetail(any()) }

        job.cancel()
    }

    @Test
    fun `Get movie detail from useCase should return error case`() = runTest {
        // Given
        val error = Throwable("error")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.getMovieDetail(any()) } returns flow

        // Then
        val job = launch {
            mockUseCase.getMovieDetail(any()).collectIndexed { index, value ->
                when (index) {
                    0 -> assertThat(value).isEqualTo(Resource.Loading(true))
                    1 -> assertThat(value).isEqualTo(Resource.Error(error))
                    2 -> assertThat(value).isEqualTo(Resource.Loading(false))
                }
            }
        }

        // When
        mockUseCase.getMovieDetail(any())
        coVerify { mockUseCase.getMovieDetail(any()) }

        job.cancel()
    }
}
