package com.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val networkStatus: SingleLiveEvent<NetworkState>?
    private val error: SingleLiveEvent<ApiError>?

    init {
        networkStatus = SingleLiveEvent()
        error = SingleLiveEvent()
    }

    fun getNetworkStatus(): LiveData<NetworkState>? {
        return networkStatus
    }

    fun setNetworkStatus(networkState: NetworkState) {
        networkStatus?.value = networkState
    }

    fun getError(): LiveData<ApiError>? {
        return error
    }

    fun setError(apiException: ApiError?) {
        error?.value = apiException
    }
}