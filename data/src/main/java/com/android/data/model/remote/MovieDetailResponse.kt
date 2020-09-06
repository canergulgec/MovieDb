package com.android.data.model.remote

data class MovieDetailResponse(
    val id: Int,
    val popularity: Double,
    val video: Boolean,
    val adult: Boolean,
    val backdrop_path: String?,
    val poster_path: String?,
    val genres: List<MovieGenre>,
    val title: String,
    val overview: String,
    val imdb_id: String,
    val runtime: Int,
    val vote_average: Double,
    val vote_count: Int,
    val release_date: String?,
)