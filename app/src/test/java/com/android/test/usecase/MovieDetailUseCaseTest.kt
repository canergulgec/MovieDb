package com.android.test.usecase

import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.android.domain.usecase.MovieDetailUseCase
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.Resource
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
    fun movieDetailFlowMustReturnSuccess() = coroutineScope.dispatcher.runBlockingTest {
        //Given
        val detailModel: MovieDetailModel = mock()
        val flow = flow {
            emit(Resource.Success(detailModel))
        }

        //When
        whenever(detailUseCase.execute(13)).thenReturn(flow)

        //Then
        val getNewToken = detailUseCase.execute(13)
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
            if (index == 2) assert(value is Resource.Loading)
        }
    }
}