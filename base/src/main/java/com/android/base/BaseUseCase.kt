package com.android.base

import com.caner.common.ApiError
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class BaseUseCase<M, Params> {

    abstract fun buildRequest(params: Params?): Flow<Resource<M>>

    fun execute(params: Params? = null) =
        buildRequest(params).catch {
            emit(Resource.Error(ApiError(4)))
        }
}
