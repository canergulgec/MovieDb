package com.android.base.ext

import com.android.base.ApiError
import io.reactivex.Single
import retrofit2.Response

fun <T : Any> Single<Response<T>>.filterResponse(): Single<T> {
    return this.flatMap {
        when (it.code()) {
            401 -> Single.never()
            in 400..501 -> Single.error<T>(ApiError(it.code(), it.message()))
            200 -> {
                if (it.isSuccessful) {
                    it.body()?.let { body ->
                        Single.just(body)
                    } ?: run {
                        Single.error<T>(ApiError(it.code(), "Response body is null"))
                    }
                } else {
                    Single.just(it.body())
                }
            }

            else -> throw  NotImplementedError()
        }
    }
}