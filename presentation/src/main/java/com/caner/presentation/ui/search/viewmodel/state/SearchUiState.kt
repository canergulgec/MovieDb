package com.caner.presentation.ui.search.viewmodel.state

import com.caner.domain.utils.state.UserMessage
import com.caner.domain.model.Movie

data class SearchUiState(
    val movieList: List<Movie> = listOf(),
    val isFetchingMovies: Boolean = false,
    val errorMessage: List<UserMessage> = listOf()
)
