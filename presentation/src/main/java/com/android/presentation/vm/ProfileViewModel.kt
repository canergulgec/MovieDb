package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.base.*
import com.android.data.Constants
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.usecase.NewSessionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val newSessionUseCase: NewSessionUseCase,
    private val preferencesUtils: SharedPreferencesUtils
) : BaseViewModel() {

    private val _newSessionLiveData: MutableLiveData<NewSessionResponse> = MutableLiveData()
    val newSessionLiveData: LiveData<NewSessionResponse> get() = _newSessionLiveData

    fun createNewSession() {
        viewModelScope.launch {
            newSessionUseCase.execute(addBody()).collect {
                when (it) {
                    is Resource.Loading -> setLoadingStatus(true)
                    is Resource.Success -> {
                        setLoadingStatus(false)
                        _newSessionLiveData.value = it.data
                    }
                    is Resource.Error -> setError(it.apiError)
                }
            }
        }
    }

    private fun addBody(): HashMap<String, Any>? {
        return object : LinkedHashMap<String, Any>() {
            init {
                put("request_token", preferencesUtils.getData(Constants.ACCESS_TOKEN, ""))
            }
        }
    }
}