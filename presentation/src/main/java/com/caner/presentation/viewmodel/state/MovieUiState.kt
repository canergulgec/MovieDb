package com.caner.presentation.viewmodel.state

import androidx.paging.PagingData
import com.caner.data.model.Movie
import kotlinx.coroutines.flow.Flow

data class MovieUiState(
    val moviesPagingFlow: Flow<PagingData<Movie>>? = null
)
