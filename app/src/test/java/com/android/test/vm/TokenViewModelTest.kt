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
import io.mockk.*
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

    private val mockUseCase = mockk<NewTokenUseCase>()

    private lateinit var viewModel: ProfileViewModel

    private val newSessionObserver = mockk<Observer<Resource<TokenResponse>>>()

    // create list to store values
    private val list = arrayListOf<Resource<TokenResponse>>()

    @Before
    fun setUpViewModel() {
        viewModel = ProfileViewModel(mockUseCase)
    }

    @Test
    fun `Get token from viewModel should return success case`() = runBlockingTest {
        // Given
        val userDetails = TokenResponse(true, "1234567")
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(userDetails))
            emit(Resource.Loading(false))
        }
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { mockUseCase.execute() } returns flow

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

        coVerify { mockUseCase.execute() }
    }

    @Test
    fun `Get token from viewModel should return error case`() = runBlockingTest {
        // Given
        val error = ApiError(code = 404)
        val flow = flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error))
            emit(Resource.Loading(false))
        }
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { mockUseCase.execute() } returns flow

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

        coVerify { mockUseCase.execute() }
    }
}
