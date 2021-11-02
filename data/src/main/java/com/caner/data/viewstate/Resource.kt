package com.caner.data.viewstate

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val apiError: ApiError) : Resource<Nothing>()
    data class Loading(val status: Boolean) : Resource<Nothing>()
    object Empty : Resource<Nothing>()
    object Initial : Resource<Nothing>()
}
