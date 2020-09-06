package com.android.data.model.remote

data class MovieVideosResponse(
    val id: Int,
    val results: List<VideoItem>
)

data class VideoItem(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)