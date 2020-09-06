package com.android.data.model

import com.android.data.model.remote.MovieGenre
import java.util.*

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double,
    val video: Boolean,
    val adult: Boolean,
    val poster: Image?,
    val backdrop: Image?,
    val genres: List<MovieGenre>,
    val title: String,
    val overview: String,
    val imdbId: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: Date?,
)