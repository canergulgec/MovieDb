package com.android.data.model.remote

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    val success: Boolean,
    @SerializedName("request_token")
    val requestToken: String
)