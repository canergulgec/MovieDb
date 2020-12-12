package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.base.BaseViewModel
import com.android.domain.paginginterface.MoviesPagingSource

class MovieViewModel @ViewModelInject constructor(
    private val pagingSource: MoviesPagingSource
) : BaseViewModel() {

    val moviePagingFlow = Pager(config = PagingConfig(
        pageSize = 20, enablePlaceholders = false
    ), pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)

    fun setMovieType(movieType: Int) {
        pagingSource.setCurrentMovieType(movieType)
    }
}