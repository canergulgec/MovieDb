package com.caner.domain.model.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenre(
    val id: Int,
    val name: String
)
