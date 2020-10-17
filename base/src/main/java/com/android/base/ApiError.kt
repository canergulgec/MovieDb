package com.android.base

data class ApiError(
    val code: Int,
    override val message: String = ""
) : Throwable()