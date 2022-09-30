package com.caner.domain.model.remote

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("genres")
    val genres: List<MovieGenre>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
)
