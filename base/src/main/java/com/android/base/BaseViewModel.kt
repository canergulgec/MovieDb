package com.android.base

import androidx.lifecycle.ViewModel
import com.caner.common.ApiError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    private val _loadingStatus = MutableStateFlow(false)
    val loadingStatus: StateFlow<Boolean> get() = _loadingStatus

    private val _errorStatus = MutableStateFlow(ApiError(code = -1))
    val errorStatus: StateFlow<ApiError> get() = _errorStatus

    fun setLoadingStatus(networkState: Boolean) {
        _loadingStatus.value = networkState
    }

    fun setErrorStatus(apiException: ApiError) {
        _errorStatus.value = apiException
    }
}