package com.android.data.model.remote

import com.google.gson.annotations.SerializedName

data class NewSessionResponse(
    val success: Boolean,
    @SerializedName("session_id")
    val sessionId: String
)