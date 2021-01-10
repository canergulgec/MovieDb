package com.android.data.model.remote

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("total_pages", alternate = ["total_results"])
    val total: Int = 0,
    val page: Int = 0,
    val results: List<MovieResponseItem>
)