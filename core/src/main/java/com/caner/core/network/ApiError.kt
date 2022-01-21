package com.caner.core.network

data class ApiError(val code: Int = -1, override val message: String? = "") : Throwable()
