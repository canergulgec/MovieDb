package com.caner.data.model.remote

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("request_token")
    val requestToken: String
)
