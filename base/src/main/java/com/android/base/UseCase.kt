package com.android.base

import androidx.annotation.Nullable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [P] -> Request Parameters
 */
abstract class UseCase<M, P> {

    abstract fun buildUseCaseObservable(@Nullable params: P?): Single<M>

    fun execute(params: P? = null): Single<M> {
        return buildUseCaseObservable(params)
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * NOT!! Observe on mainThread
     */
    fun executeInAuthenticator(params: P? = null): Single<M> {
        return buildUseCaseObservable(params)
            .subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.io())
    }
}