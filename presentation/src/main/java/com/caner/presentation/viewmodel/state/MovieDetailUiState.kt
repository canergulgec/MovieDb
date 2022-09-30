package com.caner.presentation.viewmodel.state

import com.caner.domain.model.MovieDetailModel
import com.caner.domain.utils.state.UserMessage

data class MovieDetailUiState(
    val movieDetailModel: MovieDetailModel? = null,
    val isFetchingMovieDetail: Boolean = false,
    val errorMessage: List<UserMessage> = listOf()
)
