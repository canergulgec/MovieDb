package com.caner.domain.extension

import com.caner.data.viewstate.ApiError
import com.caner.data.mapper.Mapper
import com.caner.data.viewstate.Resource
import retrofit2.Response

fun <M : Any> Response<M>.filterResponse(): Resource<M> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(it)
            } ?: return Resource.Error(ApiError(code(), "Response body is null"))
        }

        false -> Resource.Error(ApiError(code(), "Response is not successful"))
    }
}

fun <A, B> Response<A>.filterMapperResponse(mapper: Mapper<A, B>): Resource<B> {
    return when (this.isSuccessful) {
        true -> {
            this.body()?.let {
                return Resource.Success(mapper.to(it))
            } ?: return Resource.Error(ApiError(code(), "Response body is null"))
        }

        false -> Resource.Error(ApiError(code(), "Response is not successful"))
    }
}