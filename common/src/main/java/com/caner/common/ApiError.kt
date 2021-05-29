package com.caner.common

data class ApiError(
    val code: Int = -1,
    override val message: String? = ""
) : Throwable()
