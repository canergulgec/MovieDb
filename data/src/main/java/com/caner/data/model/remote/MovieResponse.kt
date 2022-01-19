package com.caner.data.model.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("total_pages", alternate = ["total_results"])
    val total: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieResponseItem>
)
