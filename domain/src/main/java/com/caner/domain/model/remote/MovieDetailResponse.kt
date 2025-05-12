package com.caner.domain.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "popularity")
    val popularity: Double? = null,
    @field:Json(name = "video")
    val video: Boolean? = false,
    @field:Json(name = "adult")
    val adult: Boolean? = false,
    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,
    @field:Json(name = "poster_path")
    val posterPath: String? = null,
    @field:Json(name = "genres")
    val genres: List<MovieGenre>? = null,
    @field:Json(name = "title")
    val title: String? = null,
    @field:Json(name = "overview")
    val overview: String? = null,
    @field:Json(name = "imdb_id")
    val imdbId: String? = null,
    @field:Json(name = "runtime")
    val runtime: Int? = null,
    @field:Json(name = "vote_average")
    val voteAverage: Double? = null,
    @field:Json(name = "vote_count")
    val voteCount: Int? = null,
    @field:Json(name = "release_date")
    val releaseDate: String? = null,
)
