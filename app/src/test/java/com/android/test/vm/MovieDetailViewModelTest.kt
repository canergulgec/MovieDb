package com.android.test.vm

import app.cash.turbine.test
import com.android.data.model.MovieDetailModel
import com.android.domain.usecase.MovieDetailUseCase
import com.android.presentation.vm.MovieDetailViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val detailUseCase: MovieDetailUseCase = mock()

    private val viewModel by lazy {
        MovieDetailViewModel(detailUseCase)
    }

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
        whenever(detailUseCase.execute(any())).thenReturn(flow)

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
        whenever(detailUseCase.execute(any())).thenReturn(flow)

        viewModel.movieDetailState.test {
            viewModel.getMovieDetail(any())

            assert(expectItem() is Resource.Initial)
            assert(expectItem() is Resource.Loading)
            assert(expectItem() is Resource.Success)
            assert(expectItem() is Resource.Loading)

            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
    }
}
