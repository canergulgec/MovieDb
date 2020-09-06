package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.base.ApiError
import com.android.base.BaseViewModel
import com.android.base.NetworkState
import com.android.base.SharedPreferencesUtils
import com.android.data.Constants
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.usecase.NewSessionUseCase
import io.reactivex.observers.DisposableSingleObserver

class ProfileViewModel @ViewModelInject constructor(
    private val newSessionUseCase: NewSessionUseCase,
    private val preferencesUtils: SharedPreferencesUtils
) : BaseViewModel() {

    private val _newSessionLiveData: MutableLiveData<NewSessionResponse> = MutableLiveData()
    val newSessionLiveData: LiveData<NewSessionResponse> get() = _newSessionLiveData

    fun createNewSession() {
        setNetworkStatus(NetworkState.Loading)

        add(
            newSessionUseCase.execute(addBody())
                .subscribeWith(object : DisposableSingleObserver<NewSessionResponse>() {
                    override fun onSuccess(t: NewSessionResponse) {
                        setNetworkStatus(NetworkState.Success)
                        _newSessionLiveData.value = t
                    }

                    override fun onError(e: Throwable){
                        //setError(e as ApiError)
                    }
                })
        )
    }

    private fun addBody(): HashMap<String, Any>? {
        return object : LinkedHashMap<String, Any>() {
            init {
                 put("request_token", preferencesUtils.getData(Constants.ACCESS_TOKEN, ""))
            }
        }
    }
}