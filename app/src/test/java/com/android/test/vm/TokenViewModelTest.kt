package com.android.test.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.data.model.remote.TokenResponse
import com.caner.common.utils.PrefStore
import com.caner.common.utils.SharedPreferencesUtils
import com.android.domain.repository.NewTokenRepository
import com.android.domain.usecase.NewTokenUseCase
import com.android.presentation.vm.ProfileViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.ApiError
import com.caner.common.Resource
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class TokenViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val newTokenRepository: NewTokenRepository = mock()

    private val sharedPref: SharedPreferencesUtils = mock()

    private val prefStore: PrefStore = mock()

    private val useCase by lazy {
        NewTokenUseCase(
            newTokenRepository,
            coroutineScope.dispatcher
        )
    }

    private val viewModel by lazy { ProfileViewModel(useCase, sharedPref, prefStore) }

    private val newSessionObserver: Observer<Resource<TokenResponse>> = mock()

    @Test
    fun newTokenFlowEmitsSuccessfullyWithArgumentCaptor() = coroutineScope.runBlockingTest {
        //Given
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Success(userDetails))
        }

        //When
        whenever(newTokenRepository.getNewToken()).thenReturn(flow)
        val captor = argumentCaptor<Resource<TokenResponse>>()
        viewModel.newSessionLiveData.observeForever(newSessionObserver) //viewModel.newSessionLiveData.getOrAwaitValue()

        //Then
        viewModel.getNewToken()

        verify(newSessionObserver, times(3)).onChanged(captor.capture())

        assertEquals(true, (captor.firstValue as Resource.Loading).status)
        assertEquals(userDetails.requestToken, (captor.secondValue as Resource.Success).data.requestToken)
        assertEquals(false, (captor.thirdValue as Resource.Loading).status)
    }

    @Test
    fun newTokenFlowEmitsError() = coroutineScope.runBlockingTest {
        //Given
        val error = ApiError(code = 404)
        val flow = flow {
            emit(Resource.Error(error))
        }

        //When
        whenever(newTokenRepository.getNewToken()).thenReturn(flow)
        val captor = argumentCaptor<Resource<TokenResponse>>()
        viewModel.newSessionLiveData.observeForever(newSessionObserver) //viewModel.newSessionLiveData.getOrAwaitValue()

        //Then
        viewModel.getNewToken()

        verify(newSessionObserver, times(3)).onChanged(captor.capture())

        assertEquals(true, (captor.firstValue as Resource.Loading).status)
        assertEquals(error.code, (captor.secondValue as Resource.Error).apiError.code)
        assertEquals(false, (captor.thirdValue as Resource.Loading).status)
    }
}