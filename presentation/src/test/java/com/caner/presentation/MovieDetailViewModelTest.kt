package com.caner.presentation

import app.cash.turbine.test
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.utils.state.Resource
import com.caner.domain.usecase.MovieDetailUseCase
import com.caner.presentation.ui.detail.viewmodel.MovieDetailViewModel
import com.caner.presentation.utils.MainCoroutineScopeRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val mockUseCase = mockk<MovieDetailUseCase>()

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUpViewModel() {
        viewModel = MovieDetailViewModel(mockUseCase)
    }

    @Test
    fun `Get movie detail from viewModel should return success case`() = runTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.getMovieDetail(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.getMovieDetail(any())

            // Then
            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(false)
            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(true)

            val state = awaitItem()
            assertThat(state.movieDetailModel).isNotEqualTo(null)
            assertThat(state.movieDetailModel?.movieId).isEqualTo(detailModel.movieId)

            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(false)

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Get movie detail from viewModel should return error case`() = runTest {
        // Given
        val error = Throwable("Unknown error")

        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.getMovieDetail(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.getMovieDetail(any())

            // Then
            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(false)
            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(true)

            val errorItem = awaitItem()
            assertThat(errorItem.errorMessage.isEmpty()).isEqualTo(false)
            assertThat(errorItem.errorMessage.first().message).isEqualTo(error.message)

            assertThat(awaitItem().isFetchingMovieDetail).isEqualTo(false)

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
