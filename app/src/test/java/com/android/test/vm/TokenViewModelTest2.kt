package com.android.test.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.data.model.remote.TokenResponse
import com.android.domain.usecase.NewTokenUseCase
import com.android.presentation.vm.ProfileViewModel
import com.android.test.utils.MainCoroutineScopeRule
import com.caner.common.ApiError
import com.caner.common.Resource
import com.caner.common.utils.PrefStore
import com.caner.common.utils.SharedPreferencesUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * This test is written with mockK
 */

@ExperimentalCoroutinesApi
class TokenViewModelTest2 {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    private lateinit var sharedPref: SharedPreferencesUtils

    @MockK
    private lateinit var prefStore: PrefStore

    @MockK
    private lateinit var useCase: NewTokenUseCase

    private val viewModel by lazy { ProfileViewModel(useCase, sharedPref, prefStore) }

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

        // When
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { useCase.execute() } returns flow

        viewModel.newSessionLiveData.observeForever(newSessionObserver)
        coEvery { newSessionObserver.onChanged(capture(tokenSlot)) } answers {
            list.add(tokenSlot.captured)
        }

        // Then
        viewModel.getNewToken()

        list.forEachIndexed { index, resource ->
            if (index == 0) assertEquals(true, (resource as Resource.Loading).status)
            if (index == 1) {
                assertEquals(
                    userDetails.requestToken,
                    (resource as Resource.Success).data.requestToken
                )
            }
            if (index == 2) assertEquals(false, (resource as Resource.Loading).status)
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

        // When
        val tokenSlot = slot<Resource<TokenResponse>>()
        coEvery { useCase.execute() } returns flow

        viewModel.newSessionLiveData.observeForever(newSessionObserver)
        coEvery { newSessionObserver.onChanged(capture(tokenSlot)) } answers {
            list.add(tokenSlot.captured)
        }

        // Then
        viewModel.getNewToken()

        list.forEachIndexed { index, resource ->
            if (index == 0) assertEquals(true, (resource as Resource.Loading).status)
            if (index == 1) assertEquals(error.code, (resource as Resource.Error).apiError.code)
            if (index == 2) assertEquals(false, (resource as Resource.Loading).status)
        }

        coVerify { useCase.execute() }
    }
}
