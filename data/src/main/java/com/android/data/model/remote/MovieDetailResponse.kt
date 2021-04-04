package com.android.data.model.remote

data class MovieDetailResponse(
    val id: Int,
    val popularity: Double? = null,
    val video: Boolean? = false,
    val adult: Boolean? = false,
    val backdrop_path: String? = null,
    val poster_path: String? = null,
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    val overview: String? = null,
    val imdb_id: String? = null,
    val runtime: Int? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val release_date: String? = null,
)
