package com.caner.domain.model

data class Movie(
    val movieId: Int,
    val popularity: Double,
    val video: Boolean,
    val poster: MovieImage?,
    val adult: Boolean,
    val backdrop: MovieImage?,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String?
)
