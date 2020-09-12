package com.android.base

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val apiError: ApiError?) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}