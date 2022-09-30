package com.caner.domain.model

data class MovieModel(
    val total: Int = 0,
    val page: Int = 0,
    val movies: List<Movie>
)
