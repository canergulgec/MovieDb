package com.android.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class UseCase<M, Params> {

    abstract suspend fun buildRequest(params: Params?): Flow<Resource<M>>

    @ExperimentalCoroutinesApi
    suspend fun execute(params: Params? = null) =
        try {
            buildRequest(params).onStart {
                emit(Resource.Loading)
            }
        } catch (exception: Exception) {
            flow { emit(Resource.Error(ApiError(1, exception.message))) }
        }
}
