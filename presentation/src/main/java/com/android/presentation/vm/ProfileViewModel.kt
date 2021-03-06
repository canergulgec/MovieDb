package com.android.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.base.BaseViewModel
import com.android.data.model.remote.TokenResponse
import com.caner.common.Constants
import com.caner.common.utils.PrefStore
import com.caner.common.utils.SharedPreferencesUtils
import com.android.domain.usecase.NewTokenUseCase
import com.caner.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val newTokenUseCase: NewTokenUseCase,
    private val preferencesUtils: SharedPreferencesUtils,
    private val prefStore: PrefStore
) : BaseViewModel() {

    private val _newSessionLiveData: MutableLiveData<Resource<TokenResponse>> = MutableLiveData()
    val newSessionLiveData: LiveData<Resource<TokenResponse>> get() = _newSessionLiveData

    fun getNewToken() {
        newTokenUseCase.execute().onEach {
            when (it) {
                is Resource.Empty -> Constants.PASS
                else -> _newSessionLiveData.value = it
            }
        }.launchIn(viewModelScope)
    }

    fun getNewTokenWithDataStore() {
        viewModelScope.launch {
            prefStore.getData(Constants.ACCESS_TOKEN_DATA_STORE).collect { _ ->
                newTokenUseCase.execute()
                    .collect {
                        when (it) {
                            is Resource.Empty -> Constants.PASS
                            else -> _newSessionLiveData.value = it
                        }
                    }
            }
        }
    }
}