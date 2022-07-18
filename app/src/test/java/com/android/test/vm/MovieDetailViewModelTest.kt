package com.android.test.vm

import app.cash.turbine.test
import com.caner.data.model.MovieDetailModel
import com.caner.presentation.viewmodel.MovieDetailViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.android.test.utils.`should be`
import com.android.test.utils.`should not be`
import com.caner.core.network.Resource
import com.caner.domain.usecase.MovieDetailUseCase
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
            awaitItem().isFetchingMovieDetail `should be` false
            awaitItem().isFetchingMovieDetail `should be` true

            val state = awaitItem()
            state.movieDetailModel `should not be` null
            state.movieDetailModel?.movieId `should be` detailModel.movieId

            awaitItem().isFetchingMovieDetail `should be` false

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
            awaitItem().isFetchingMovieDetail `should be` false
            awaitItem().isFetchingMovieDetail `should be` true

            val errorItem = awaitItem()
            errorItem.errorMessage.isEmpty() `should be` false
            errorItem.errorMessage.first().message `should be` error.message

            awaitItem().isFetchingMovieDetail `should be` false

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
