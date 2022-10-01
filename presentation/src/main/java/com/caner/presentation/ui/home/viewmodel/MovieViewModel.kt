package com.caner.presentation.ui.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.paging.cachedIn
import com.caner.core.Constants
import com.caner.navigation.NavigationDispatcher
import com.caner.domain.usecase.MovieUseCase
import com.caner.presentation.ui.home.viewmodel.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val navigationDispatcher: NavigationDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState())
    val movieUiState: StateFlow<MovieUiState> = _movieUiState.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.MOVIE_PATH)?.let { path ->
            getMovies(moviePath = path)
        }
    }

    private fun getMovies(moviePath: String) {
        val movies = movieUseCase.getMovies(path = moviePath).cachedIn(viewModelScope)
        _movieUiState.update { it.copy(moviesPagingFlow = movies) }
    }

    fun navigateToMovieDetail(direction: NavDirections) {
        viewModelScope.launch {
            navigationDispatcher.navigateTo(direction)
        }
    }
}
