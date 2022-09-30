package com.caner.presentation.viewmodel.state

import com.caner.domain.state.UserMessage
import com.caner.domain.model.Movie

data class SearchUiState(
    val movieList: List<Movie> = listOf(),
    val isFetchingMovies: Boolean = false,
    val errorMessage: List<UserMessage> = listOf()
)
