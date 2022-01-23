package com.android.test.usecase

import com.android.test.utils.MainCoroutineScopeRule
import com.android.test.utils.`should be`
import com.caner.core.network.ApiError
import com.caner.core.network.Resource
import com.caner.data.model.MovieDetailModel
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
class MovieDetailUseCaseTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private lateinit var useCase: MovieDetailUseCase

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
            useCase.execute(any()).collectIndexed { index, value ->
                when (index) {
                    0 -> value `should be` Resource.Loading(true)
                    1 -> value `should be` Resource.Success(detailModel)
                    2 -> value `should be` Resource.Loading(false)
                }
            }
        }

        // When
        useCase.execute(any())
        coVerify { useCase.execute(any()) }

        job.cancel()
    }

    @Test
    fun movieDetailFlowMustReturnError() = runBlockingTest {
        // Given
        val error = ApiError(1, "error")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error))
            emit(Resource.Loading(false))
        }
        coEvery { useCase.execute(any()) } returns flow

        // Then
        val job = launch {
            useCase.execute(any()).collectIndexed { index, value ->
                when (index) {
                    0 -> value `should be` Resource.Loading(true)
                    1 -> value `should be` Resource.Error(error)
                    2 -> value `should be` Resource.Loading(false)
                }
            }
        }

        // When
        useCase.execute(any())
        coVerify { useCase.execute(any()) }

        job.cancel()
    }
}
