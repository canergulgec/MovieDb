package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.base.BaseViewModel
import com.android.presentation.pagingsource.MoviesPagingSource

class MovieViewModel @ViewModelInject constructor(
    private val pagingSource: MoviesPagingSource,
) : BaseViewModel() {

    val moviePagingFlow = Pager(config = PagingConfig(
        pageSize = 20, enablePlaceholders = true,
        maxSize = 30, prefetchDistance = 5, initialLoadSize = 40
    ), pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)

    fun setMovieType(movieType: Int) {
        pagingSource.setCurrentMovieType(movieType)
    }
}