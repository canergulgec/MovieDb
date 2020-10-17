package com.android.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class UseCase<M, Params> {

    abstract fun buildRequest(params: Params?): Flow<Resource<M>>

    @ExperimentalCoroutinesApi
    fun execute(params: Params? = null) =
        buildRequest(params).onStart {
            emit(Resource.Loading)
        }.catch {
            emit(Resource.Error(ApiError(1)))
        }
}
