package com.caner.common

data class ApiError(
    val code: Int,
    override val message: String = ""
) : Throwable()