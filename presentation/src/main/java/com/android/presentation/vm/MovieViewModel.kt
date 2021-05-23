package com.android.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.data.mapper.MovieMapper
import com.android.domain.paginginterface.MoviesPagingSource
import com.android.domain.repository.MovieRepository
import com.caner.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val movieType = savedStateHandle.get<Int>(Constants.MOVIE_TYPE) ?: 1
        MoviesPagingSource.movieType = movieType
    }

    val moviePagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { MoviesPagingSource(movieRepository, movieMapper) }
    ).flow.cachedIn(viewModelScope)
}
