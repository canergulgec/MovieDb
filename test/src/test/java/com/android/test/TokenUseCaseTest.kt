package com.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.base.Resource
import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.android.domain.usecase.NewTokenUseCase
import com.android.test.util.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TokenUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val repository: NewTokenRepository = mock()

    private lateinit var usecase: NewTokenUseCase

    @Before
    fun setup() {
        usecase = NewTokenUseCase(repository, coroutineScope.dispatcher)
    }

    @Test
    fun `new token flow emits successfully`() = runBlockingTest {
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(userDetails))
        }

        whenever(usecase.execute()).thenReturn(flow)

        val getNewToken = usecase.execute()
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value == Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
        }
    }
}