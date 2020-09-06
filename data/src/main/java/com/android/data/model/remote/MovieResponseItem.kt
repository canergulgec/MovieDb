package com.android.data.model.remote

import com.google.gson.annotations.SerializedName

data class MovieResponseItem(
    val id: Int,
    val popularity: Double,
    val video: Boolean,
    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String?
)