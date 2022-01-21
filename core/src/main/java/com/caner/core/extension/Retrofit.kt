package com.caner.core.extension

import com.caner.core.network.ApiError
import com.caner.core.network.Resource
import retrofit2.Response

fun <M : Any> Response<M>.toResource(): Resource<M> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(it)
            } ?: return Resource.Error(ApiError(code(), "Response body is null"))
        }

        false -> Resource.Error(ApiError(code(), message()))
    }
}
