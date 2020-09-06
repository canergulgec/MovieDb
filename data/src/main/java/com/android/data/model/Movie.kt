package com.android.data.model

import java.util.*

data class Movie(
    val movieId: Int,
    val popularity: Double,
    val video: Boolean,
    val poster: Image?,
    val adult: Boolean,
    val backdrop: Image?,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: Date?
)