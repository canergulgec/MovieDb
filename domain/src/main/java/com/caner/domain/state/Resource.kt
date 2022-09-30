package com.caner.domain.state

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()
    data class Loading(val status: Boolean) : Resource<Nothing>()
}
