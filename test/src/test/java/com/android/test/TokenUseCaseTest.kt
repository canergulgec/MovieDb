package com.android.test

import com.android.base.Resource
import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.android.domain.usecase.NewTokenUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test

@ExperimentalCoroutinesApi
class TokenUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val newTokenRepository = mock<NewTokenRepository>()
    private val newTokenUseCase = NewTokenUseCase(newTokenRepository, testDispatcher)

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        val userDetails = TokenResponse(true, "1234567")
        newTokenUseCase.stub {
            onBlocking { execute() } doReturn flow {
                emit(Resource.Loading)
                emit(Resource.Success(userDetails))
            }
        }
        val flow = newTokenUseCase.execute()
        flow.collectIndexed { index, value ->
            if (index == 0) assert(value == Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
        }
    }
}