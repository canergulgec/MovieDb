package com.caner.core.extension

import com.caner.core.network.Resource
import retrofit2.Response

fun <M : Any> Response<M>.toResource(): Resource<M> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(it)
            } ?: return Resource.Error(Throwable(message = "Response body is null"))
        }

        false -> Resource.Error(Throwable(message()))
    }
}
