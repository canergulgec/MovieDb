package com.caner.domain.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseItem(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "video")
    val video: Boolean,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "release_date")
    val releaseDate: String?
)
