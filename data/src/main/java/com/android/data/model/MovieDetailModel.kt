package com.android.data.model

import com.android.data.model.remote.MovieGenre
import java.util.Date

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val poster: Image? = null,
    val backdrop: Image? = null,
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    val overview: String? = null,
    val imdbId: String? = null,
    val runtime: Int? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val releaseDate: Date? = null
)
