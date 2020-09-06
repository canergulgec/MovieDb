package com.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable
    private val networkStatus: SingleLiveEvent<NetworkState>?
    private val error: SingleLiveEvent<ApiError>?

    init {
        networkStatus = SingleLiveEvent()
        error = SingleLiveEvent()
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
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

    fun setError(apiException: ApiError) {
        error?.value = apiException
    }

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}