package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.base.BaseViewModel

class SearchViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _postListLiveData: MutableLiveData<String> = MutableLiveData()
    val postListLiveData: LiveData<String> get() = _postListLiveData

}