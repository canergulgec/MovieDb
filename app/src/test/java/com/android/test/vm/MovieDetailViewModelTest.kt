package com.android.test.vm

import app.cash.turbine.test
import com.caner.data.model.MovieDetailModel
import com.caner.presentation.viewmodel.MovieDetailViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.android.test.utils.`should be`
import com.android.test.utils.`should not be`
import com.caner.core.network.ApiError
import com.caner.core.network.Resource
import com.caner.domain.usecase.MovieDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
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

    @MockK
    private lateinit var useCase: MovieDetailUseCase

    private val viewModel by lazy {
        MovieDetailViewModel(useCase)
    }

    @Before
    fun setUp() =
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun movieDetailFlowMustReturnSuccess() = runBlockingTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }
        coEvery { useCase.execute(any()) } returns flow

        // Then
        val job = launch {
            viewModel.uiState.collectIndexed { index, value ->
                when (index) {
                    0 -> assert(value.isFetchingMovieDetail)
                    1 -> {
                        value.movieDetailModel `should not be` null
                        value.movieDetailModel?.movieId `should be` detailModel.movieId
                        value.isFetchingMovieDetail `should be` false
                    }
                }
            }
        }

        // When
        viewModel.getMovieDetail(any())
        coVerify { useCase.execute(any()) }

        job.cancel()
    }

    @Test
    fun movieDetailFlowMustReturnSuccessWithTurbine() = runBlockingTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }
        coEvery { useCase.execute(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.getMovieDetail(any())

            // Then
            awaitItem().isFetchingMovieDetail `should be` true
            val state = awaitItem()
            state.movieDetailModel `should not be` null
            state.movieDetailModel?.movieId `should be` detailModel.movieId

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { useCase.execute(any()) }
    }

    @Test
    fun movieDetailFlowMustReturnErrorWithTurbine() = runBlockingTest {
        // Given
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(ApiError(1, "Unknown error")))
            emit(Resource.Loading(false))
        }
        coEvery { useCase.execute(any()) } returns flow

        viewModel.uiState.test {
            // When
            viewModel.getMovieDetail(any())

            // Then
            awaitItem().isFetchingMovieDetail `should be` true
            awaitItem().userMessages.isNotEmpty() `should be` true

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
