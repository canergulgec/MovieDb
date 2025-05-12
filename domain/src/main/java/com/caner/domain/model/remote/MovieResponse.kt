package com.caner.domain.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @field:Json(name = "total_pages")
    val total: Int = 0,
    @field:Json(name = "page")
    val page: Int = 0,
    @field:Json(name = "results")
    var results: List<MovieResponseItem>
)
