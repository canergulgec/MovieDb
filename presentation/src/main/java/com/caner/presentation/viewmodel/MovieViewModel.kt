package com.caner.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.caner.data.repository.MovieRepository
import com.caner.core.Constants
import com.caner.core.network.HttpParams
import com.caner.domain.mapper.MovieMapper
import com.caner.presentation.viewmodel.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState())
    val movieUiState: StateFlow<MovieUiState> = _movieUiState.asStateFlow()

    init {
        val moviePath =
            savedStateHandle.get<String>(Constants.MOVIE_PATH) ?: HttpParams.NOW_PLAYING_MOVIES
        getMovies(moviePath)
    }

    private fun getMovies(moviePath: String) {
        val movies = repository.getMovies(moviePath)
            .map { pagingData ->
                pagingData.map {
                    mapper.toMovie(it)
                }
            }
            .cachedIn(viewModelScope)
        _movieUiState.update { it.copy(moviesPagingFlow = movies) }
    }
}
