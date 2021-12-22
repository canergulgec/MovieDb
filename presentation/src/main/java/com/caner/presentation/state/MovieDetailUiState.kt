package com.caner.presentation.state

import com.caner.data.model.MovieDetailModel
import com.caner.data.model.remote.MovieGenre
import com.caner.data.viewstate.UserMessage

data class MovieDetailUiState(
        val movieDetailModel: MovieDetailModel? = null,
        val isFetchingMovieDetail: Boolean = false,
        val userMessages: List<UserMessage> = emptyList()
)
