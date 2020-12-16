package com.android.base

import androidx.lifecycle.ViewModel
import com.caner.common.ApiError
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    private val loadingStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val error: MutableStateFlow<ApiError> = MutableStateFlow(ApiError(-1))

    fun getLoadingStatus(): MutableStateFlow<Boolean> {
        return loadingStatus
    }

    fun setLoadingStatus(networkState: Boolean) {
        loadingStatus.value = networkState
    }

    fun getError(): MutableStateFlow<ApiError> {
        return error
    }

    fun setError(apiException: ApiError) {
        error.value = apiException
        loadingStatus.value = false
    }
}