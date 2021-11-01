package com.caner.domain.viewstate

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val apiError: ApiError) : Resource<Nothing>()
    class Loading(val status: Boolean) : Resource<Nothing>()
    object Empty : Resource<Nothing>()
    object Initial : Resource<Nothing>()
}
