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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository,
    mapper: MovieMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var moviePath: String = HttpParams.NOW_PLAYING_MOVIES

    init {
        moviePath =
            savedStateHandle.get<String>(Constants.MOVIE_PATH) ?: HttpParams.NOW_PLAYING_MOVIES
    }

    val moviePagingFlow = repository.getMovies(moviePath)
        .map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
        .cachedIn(viewModelScope)
}
