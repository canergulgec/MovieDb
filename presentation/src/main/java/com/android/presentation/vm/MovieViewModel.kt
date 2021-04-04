package com.android.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.domain.paginginterface.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val pagingSource: MoviesPagingSource
) : ViewModel() {

    val moviePagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)

    fun setMovieType(movieType: Int) {
        pagingSource.setCurrentMovieType(movieType)
    }
}
