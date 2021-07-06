package com.android.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.domain.paginginterface.MoviesPagingSource
import com.android.domain.repository.MovieRepository
import com.caner.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val movieType = savedStateHandle.get<Int>(Constants.MOVIE_TYPE) ?: 1
        MoviesPagingSource.movieType = movieType
    }

    val moviePagingFlow = movieRepository.getMovies()
        .cachedIn(viewModelScope)
}
