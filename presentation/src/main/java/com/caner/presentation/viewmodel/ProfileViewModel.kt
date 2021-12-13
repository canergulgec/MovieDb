package com.caner.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.data.model.remote.TokenResponse
import com.caner.domain.usecase.NewTokenUseCase
import com.caner.data.viewstate.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
        private val newTokenUseCase: NewTokenUseCase
) : ViewModel() {

    private val _newSessionLiveData: MutableLiveData<Resource<TokenResponse>> = MutableLiveData()
    val newSessionLiveData: LiveData<Resource<TokenResponse>> get() = _newSessionLiveData

    fun getNewToken() {
        newTokenUseCase.execute().onEach {
            _newSessionLiveData.value = it
        }.launchIn(viewModelScope)
    }
}
