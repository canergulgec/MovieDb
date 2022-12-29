package com.caner.presentation

import app.cash.turbine.test
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.utils.state.Resource
import com.caner.domain.usecase.MovieDetailUseCase
import com.caner.presentation.ui.detail.viewmodel.MovieDetailViewModel
import com.caner.presentation.utils.MainCoroutineScopeRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
    fun `Should return successful response when asked for movie detail`() = runTest {
        // Given
        val id = 1
        val mockDetailModel = mockk<MovieDetailModel> {
            every { movieId } returns id
        }
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(mockDetailModel))
            emit(Resource.Loading(false))
        }
        coEvery { mockUseCase.getMovieDetail(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.getMovieDetail(any())

            // Then
            assertThat(awaitItem().isFetchingMovieDetail).isFalse()
            assertThat(awaitItem().isFetchingMovieDetail).isTrue()

            val state = awaitItem()
            assertThat(state.movieDetailModel).isNotNull()
            assertThat(state.movieDetailModel?.movieId).isEqualTo(id)
            assertThat(awaitItem().isFetchingMovieDetail).isFalse()

            ensureAllEventsConsumed()
        }
        // Verify
        coVerify(exactly = 1) { mockUseCase.getMovieDetail(any()) }
    }

    @Test
    fun `Should return error when asked for movie detail`() = runTest {
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
            assertThat(awaitItem().isFetchingMovieDetail).isFalse()
            assertThat(awaitItem().isFetchingMovieDetail).isTrue()

            val errorItem = awaitItem()
            assertThat(errorItem.errorMessage.isEmpty()).isFalse()
            assertThat(errorItem.errorMessage.first().message).isEqualTo(error.message)
            assertThat(awaitItem().isFetchingMovieDetail).isFalse()

            ensureAllEventsConsumed()
        }
        // Verify
        coVerify(exactly = 1) { mockUseCase.getMovieDetail(any()) }
    }
}
