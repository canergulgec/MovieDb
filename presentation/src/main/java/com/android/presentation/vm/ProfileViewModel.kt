package com.android.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.base.BaseViewModel
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
                is Resource.Empty -> Constants.pass
            }
        }.launchIn(viewModelScope)
    }

    fun getNewTokenWithDataStore() {
        viewModelScope.launch {
            prefStore.getData(Constants.ACCESS_TOKEN_DATA_STORE).collect { _ ->
                newTokenUseCase.execute()
                    .collect {
                        when (it) {
                            is Resource.Loading -> setLoadingStatus(true)
                            is Resource.Success -> {
                                setLoadingStatus(false)
                                _newSessionLiveData.value = it.data.requestToken
                            }
                            is Resource.Error -> setError(it.apiError)
                            is Resource.Empty -> Constants.pass
                        }
                    }
            }
        }
    }
}