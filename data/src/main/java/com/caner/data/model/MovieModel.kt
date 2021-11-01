package com.caner.data.model

data class MovieModel(
    val total: Int = 0,
    val page: Int = 0,
    val movies: List<Movie>
) {

    val endOfPage = total == page

    data class MovieRemoteKeys(
        val movieId: Int,
        val prevKey: Int?,
        val nextKey: Int?
    )
}
