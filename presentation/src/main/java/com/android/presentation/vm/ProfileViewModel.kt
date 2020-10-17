package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.base.*
import com.android.data.Constants
import com.android.data.utils.DataStoreUtils
import com.android.data.utils.SharedPreferencesUtils
import com.android.domain.usecase.NewTokenUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ProfileViewModel @ViewModelInject constructor(
    private val newTokenUseCase: NewTokenUseCase,
    private val preferencesUtils: SharedPreferencesUtils,
    private val prefUtils: DataStoreUtils
) : BaseViewModel() {

    private val _newSessionLiveData: MutableLiveData<String> = MutableLiveData()
    val newSessionLiveData: LiveData<String> get() = _newSessionLiveData

    fun getNewToken() {
        newTokenUseCase.execute().onEach {
            when (it) {
                is Resource.Loading -> setLoadingStatus(true)
                is Resource.Success -> {
                    setLoadingStatus(false)
                    _newSessionLiveData.value = it.data.requestToken
                }
                is Resource.Error -> setError(it.apiError)
            }
        }.launchIn(viewModelScope)
    }

    fun getNewTokenWithDataStore() {
        viewModelScope.launch {
            prefUtils.getData(Constants.ACCESS_TOKEN_DATA_STORE).collect { _ ->
                newTokenUseCase.execute()
                    .collect {
                        when (it) {
                            is Resource.Loading -> setLoadingStatus(true)
                            is Resource.Success -> {
                                setLoadingStatus(false)
                                _newSessionLiveData.value = it.data.requestToken
                            }
                            is Resource.Error -> setError(it.apiError)
                        }
                    }
            }
        }
    }
}