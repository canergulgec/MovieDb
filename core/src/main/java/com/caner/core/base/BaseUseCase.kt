package com.caner.core.base

import com.caner.core.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class BaseUseCase<M, Params> {

    abstract fun buildResponse(params: Params?): Flow<Resource<M>>

    fun execute(params: Params? = null) =
        buildResponse(params).catch { error ->
            emit(Resource.Error(Throwable(message = error.message)))
            emit(Resource.Loading(false))
        }
            .flowOn(Dispatchers.IO)
}
