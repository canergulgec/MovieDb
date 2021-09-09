package com.android.test.vm

import app.cash.turbine.test
import com.android.data.model.MovieDetailModel
import com.android.domain.usecase.MovieDetailUseCase
import com.android.presentation.vm.MovieDetailViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.ApiError
import com.caner.common.Resource
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

/**
 * This test is written with mockK
 */

@ExperimentalTime
@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private lateinit var detailUseCase: MovieDetailUseCase

    private val viewModel by lazy {
        MovieDetailViewModel(detailUseCase)
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun movieDetailFlowMustReturnSuccess() = runBlockingTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(detailModel))
            emit(Resource.Loading(false))
        }

        // When
        coEvery { detailUseCase.execute(any()) } returns flow

        // Then
        val job = launch {
            viewModel.movieDetailState.collectIndexed { index, value ->
                when (index) {
                    0 -> assert(value is Resource.Initial)
                    1 -> assert(value is Resource.Loading)
                    2 -> assert(value is Resource.Success)
                    3 -> assert(value is Resource.Loading)
                }
            }
        }

        viewModel.getMovieDetail(any())
        coVerify { detailUseCase.execute(any()) }

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

        // When
        coEvery { detailUseCase.execute(any()) } returns flow

        viewModel.movieDetailState.test {
            viewModel.getMovieDetail(any())

            assert(expectItem() is Resource.Initial)
            assert(expectItem() is Resource.Loading)
            assert(expectItem() is Resource.Success)
            assert(expectItem() is Resource.Loading)

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { detailUseCase.execute(any()) }
    }

    @Test
    fun movieDetailFlowMustReturnErrorWithTurbine() = runBlockingTest {
        // Given
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(ApiError(1,"Unknown error")))
            emit(Resource.Loading(false))
        }

        // When
        coEvery { detailUseCase.execute(any()) } returns flow

        viewModel.movieDetailState.test {
            viewModel.getMovieDetail(any())

            assert(expectItem() is Resource.Initial)
            assert(expectItem() is Resource.Loading)
            assert(expectItem() is Resource.Error)
            assert(expectItem() is Resource.Loading)

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
