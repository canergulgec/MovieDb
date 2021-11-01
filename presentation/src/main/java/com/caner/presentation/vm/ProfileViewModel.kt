package com.caner.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.data.model.remote.TokenResponse
import com.caner.domain.usecase.NewTokenUseCase
import com.caner.data.local.PrefKeys
import com.caner.domain.viewstate.Resource
import com.caner.data.local.PrefStore
import com.caner.data.local.SharedPreferencesUtils
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
