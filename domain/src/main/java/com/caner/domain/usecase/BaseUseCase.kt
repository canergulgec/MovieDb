package com.caner.domain.usecase

import com.caner.core.network.Resource
import com.caner.core.network.ApiError
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
        buildRequest(params).catch { error ->
            emit(Resource.Error(ApiError(4, error.message)))
        }
}
