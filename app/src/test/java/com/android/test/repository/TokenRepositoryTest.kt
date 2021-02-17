package com.android.test.repository

import com.caner.common.Resource
import com.android.data.model.remote.TokenResponse
import com.android.domain.repository.NewTokenRepository
import com.android.test.utils.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TokenRepositoryTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val repository: NewTokenRepository = mock()

    @Test
    fun `new token flow emits successfully`() = runBlocking {
        //Given
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(userDetails))
        }

        //When
        whenever(repository.getNewToken()).thenReturn(flow)

        //Then
        val getNewToken = repository.getNewToken()
        getNewToken.collectIndexed { index, value ->
            if (index == 0) assert(value is Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
        }
    }
}