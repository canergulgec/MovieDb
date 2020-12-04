package com.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.base.Resource
import com.android.data.model.remote.TokenResponse
import com.android.data.utils.DataStoreUtils
import com.android.data.utils.SharedPreferencesUtils
import com.android.domain.repository.NewTokenRepository
import com.android.domain.usecase.NewTokenUseCase
import com.android.presentation.vm.ProfileViewModel
import com.android.test.util.MainCoroutineScopeRule
import com.android.test.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
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

    private val dataStoreUtils: DataStoreUtils = mock()

    private lateinit var usecase: NewTokenUseCase

    private lateinit var viewModel: ProfileViewModel

    private val newSessionObserver: Observer<String> = mock()

    @Before
    fun setup() {
        usecase = NewTokenUseCase(newTokenRepository, coroutineScope.dispatcher)
        viewModel = ProfileViewModel(usecase, sharedPref, dataStoreUtils)
    }

    @Test
    fun `new token flow emits successfully with argument captor`() = runBlockingTest {
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(userDetails))
        }

        whenever(usecase.execute()).thenReturn(flow)
        val captor = argumentCaptor<String>()
        viewModel.newSessionLiveData.observeForever(newSessionObserver)

        viewModel.getNewToken()

        verify(newSessionObserver, times(1)).onChanged(captor.capture())
        assertEquals(userDetails.requestToken, captor.firstValue)
    }

    @Test
    fun `new token flow emits successfully with liveData util`() = runBlockingTest {
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading)
            emit(Resource.Success(userDetails))
        }

        whenever(usecase.execute()).thenReturn(flow)
        viewModel.getNewToken()

        assertEquals(userDetails.requestToken, viewModel.newSessionLiveData.getOrAwaitValue())
    }
}