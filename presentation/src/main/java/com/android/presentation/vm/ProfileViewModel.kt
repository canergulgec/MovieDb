package com.android.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.data.model.remote.TokenResponse
import com.android.domain.usecase.NewTokenUseCase
import com.android.data.local.PrefKeys
import com.caner.common.Resource
import com.android.data.local.PrefStore
import com.android.data.local.SharedPreferencesUtils
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
) : ViewModel() {

    private val _newSessionLiveData: MutableLiveData<Resource<TokenResponse>> = MutableLiveData()
    val newSessionLiveData: LiveData<Resource<TokenResponse>> get() = _newSessionLiveData

    fun getNewToken() {
        newTokenUseCase.execute().onEach {
            _newSessionLiveData.value = it
        }.launchIn(viewModelScope)
    }

    fun getNewTokenWithDataStore() {
        viewModelScope.launch {
            prefStore.getData(PrefKeys.ACCESS_TOKEN_DATA_STORE).collect { _ ->
                newTokenUseCase.execute()
                    .collect {
                        _newSessionLiveData.value = it
                    }
            }
        }
    }
}
