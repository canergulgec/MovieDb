package com.caner.presentation.viewmodel.state

import com.caner.data.model.MovieDetailModel
import com.caner.core.network.UserMessage

data class MovieDetailUiState(
        val movieDetailModel: MovieDetailModel? = null,
        val isFetchingMovieDetail: Boolean = false,
        val userMessages: List<UserMessage> = emptyList()
)
