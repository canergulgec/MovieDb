package com.android.test.usecase

import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.android.domain.usecase.MovieDetailUseCase
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.ApiError
import com.caner.common.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val detailRepository: MovieDetailRepository = mock()

    private val detailUseCase by lazy {
        MovieDetailUseCase(detailRepository, coroutineScope.dispatcher)
    }

    @Test
    fun movieDetailFlowMustReturnSuccess() = coroutineScope.runBlockingTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Success(detailModel))
        }

        // When
        whenever(detailRepository.getMovieDetail(any())).thenReturn(flow)

        // Then
        val getNewToken = detailUseCase.execute(any())
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Loading)
            if (index == 1) {
                assert(value is Resource.Success)
                if (value is Resource.Success) {
                    assert(value.data == detailModel)
                }
            }
            if (index == 2) assert(value is Resource.Loading)
        }
    }

    @Test
    fun movieDetailFlowErrorCase() = coroutineScope.runBlockingTest {
        // Given
        val error = ApiError(code = 1)
        val flow = flow {
            emit(Resource.Error(error))
        }

        // When
        whenever(detailRepository.getMovieDetail(any())).thenReturn(flow)

        // Then
        val getNewToken = detailUseCase.execute(any())
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Loading)
            if (index == 1) {
                assert(value is Resource.Error)
                if (value is Resource.Error) {
                    assert(value.apiError.code == error.code)
                }
            }
            if (index == 2) assert(value is Resource.Loading)
        }
    }
}
