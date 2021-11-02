package com.android.test.usecase

import com.caner.data.model.MovieDetailModel
import com.caner.domain.repository.MovieDetailRepository
import com.caner.domain.usecase.MovieDetailUseCase
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.data.viewstate.ApiError
import com.caner.data.viewstate.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any

/**
 * This test is written with mockK
 */

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private lateinit var detailRepository: MovieDetailRepository

    private val detailUseCase by lazy {
        MovieDetailUseCase(detailRepository, coroutineScope.dispatcher)
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun movieDetailFlowMustReturnSuccess() = coroutineScope.runBlockingTest {
        // Given
        val detailModel = MovieDetailModel(movieId = 1)
        val flow = flow {
            emit(Resource.Success(detailModel))
        }

        // When
        coEvery { detailRepository.getMovieDetail(any()) } returns flow
        val getNewToken = detailUseCase.execute(any())

        // Then
        coVerify { detailRepository.getMovieDetail(any()) }

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
        coEvery { detailRepository.getMovieDetail(any()) } returns flow
        val getNewToken = detailUseCase.execute(any())

        // Then
        coVerify { detailRepository.getMovieDetail(any()) }

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
