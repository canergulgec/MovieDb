package com.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val loadingStatus: SingleLiveEvent<Boolean>?
    private val error: SingleLiveEvent<ApiError>?

    init {
        loadingStatus = SingleLiveEvent()
        error = SingleLiveEvent()
    }

    fun getLoadingStatus(): LiveData<Boolean>? {
        return loadingStatus
    }

    fun setLoadingStatus(networkState: Boolean) {
        loadingStatus?.value = networkState
    }

    fun getError(): LiveData<ApiError>? {
        return error
    }

    fun setError(apiException: ApiError?) {
        error?.value = apiException
    }
}