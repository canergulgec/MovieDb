package com.android.base.ext

import com.android.base.ApiError
import com.android.base.Mapper
import com.android.base.Resource
import retrofit2.Response

fun <M : Any> Response<M>.filterResponse(): Resource<M> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(it)
            } ?: return Resource.Error(ApiError(2, "Response body is null"))
        }

        false -> Resource.Error(ApiError(1, "Response is not successful"))
    }
}

fun <A, B> Response<A>.filterMapperResponse(mapper: Mapper<A, B>): Resource<B> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(mapper.to(it))
            } ?: return Resource.Error(ApiError(2, "Response body is null"))
        }

        false -> Resource.Error(ApiError(1, "Response is not successful"))
    }
}