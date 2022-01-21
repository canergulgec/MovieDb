package com.caner.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.caner.domain.repository.MovieRepository
import com.caner.core.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieType: Int = Constants.NOW_PLAYING_MOVIES
    init {
        movieType =
            savedStateHandle.get<Int>(Constants.MOVIE_TYPE) ?: Constants.NOW_PLAYING_MOVIES
    }

    val moviePagingFlow = repository.getMovies(movieType)
        .cachedIn(viewModelScope)
}
