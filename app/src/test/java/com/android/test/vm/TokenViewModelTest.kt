package com.android.test.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.caner.data.model.remote.TokenResponse
import com.caner.domain.usecase.NewTokenUseCase
import com.caner.presentation.viewmodel.ProfileViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.android.test.utils.`should be`
import com.caner.core.network.ApiError
import com.caner.core.network.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TokenViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private lateinit var useCase: NewTokenUseCase

    private val viewModel by lazy { ProfileViewModel(useCase) }

    @MockK
    private lateinit var newSessionObserver: Observer<Resource<TokenResponse>>

    // create list to store values
    private val list = arrayListOf<Resource<TokenResponse>>()

    @Before
    fun setUp() =
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

    @Test
    fun newTokenFlowEmitsSuccessfullyWithArgumentCaptor() = runBlockingTest {
        // Given
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(userDetails))
            emit(Resource.Loading(false))
        }
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { useCase.execute() } returns flow

        viewModel.newSessionLiveData.observeForever(newSessionObserver)
        coEvery { newSessionObserver.onChanged(capture(tokenSlot)) } answers {
            list.add(tokenSlot.captured)
        }

        // When
        viewModel.getNewToken()

        // Then
        list.forEachIndexed { index, resource ->
            if (index == 0 && resource is Resource.Loading) {
                resource.status `should be` true
            }
            if (index == 1 && resource is Resource.Success) {
                resource.data.requestToken `should be` userDetails.requestToken
            }
            if (index == 2 && resource is Resource.Loading) {
                resource.status `should be` false
            }
        }

        coVerify { useCase.execute() }
    }

    @Test
    fun newTokenFlowEmitsError() = coroutineScope.runBlockingTest {
        // Given
        val error = ApiError(code = 404)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error))
            emit(Resource.Loading(false))
        }
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { useCase.execute() } returns flow

        viewModel.newSessionLiveData.observeForever(newSessionObserver)
        coEvery { newSessionObserver.onChanged(capture(tokenSlot)) } answers {
            list.add(tokenSlot.captured)
        }

        // When
        viewModel.getNewToken()

        // Then
        list.forEachIndexed { index, resource ->
            if (index == 0 && resource is Resource.Loading) {
                resource.status `should be` true
            }
            if (index == 1 && resource is Resource.Error) {
                resource.error.code `should be` error.code
            }
            if (index == 2 && resource is Resource.Loading) {
                resource.status `should be` false
            }
        }

        coVerify { useCase.execute() }
    }
}
