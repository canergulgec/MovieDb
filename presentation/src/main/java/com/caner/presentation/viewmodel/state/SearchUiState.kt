package com.caner.presentation.viewmodel.state

import com.caner.core.network.UserMessage
import com.caner.data.model.Movie

data class SearchUiState(
    val movieList: List<Movie> = listOf(),
    val isFetchingMovies: Boolean = false,
    val errorMessage: List<UserMessage> = listOf()
)
