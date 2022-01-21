package com.caner.core.network

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: ApiError) : Resource<Nothing>()
    data class Loading(val status: Boolean) : Resource<Nothing>()
}
