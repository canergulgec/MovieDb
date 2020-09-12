package com.android.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class UseCase<M, Params> {

    abstract suspend fun buildRequest(params: Params?): Flow<Resource<M>>

    suspend fun execute(params: Params? = null): Flow<Resource<M>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(ApiError(1, exception.message))) }
        }
    }
}