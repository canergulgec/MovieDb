package com.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.base.Resource
import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.android.domain.usecase.NewTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TokenUseCaseTest2 {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var newTokenRepository: NewTokenRepository

    private lateinit var newTokenUseCase: NewTokenUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        newTokenUseCase = NewTokenUseCase(newTokenRepository, coroutineScope.dispatcher)
    }

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(userDetails))
        }

        `when`(newTokenUseCase.execute()).thenReturn(flow)

        val getNewToken = newTokenUseCase.execute()
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value == Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
        }
    }
}