package com.android.data.model.remote

data class MovieImagesResponse(
    val backdrops: List<BackdropItem>,
    val id: Int,
    val posters: List<PosterItem>
)

data class BackdropItem(
    val id: Int,
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: Any,
    val width: Int
)

data class PosterItem(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val width: Int
)