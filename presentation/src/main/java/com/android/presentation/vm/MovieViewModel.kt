package com.android.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.android.base.BaseViewModel
import com.android.data.model.Movie
import com.android.presentation.pagingsource.MoviesPagingSource

class MovieViewModel @ViewModelInject constructor(
    private val pagingSource: MoviesPagingSource,
) : BaseViewModel() {

    private val _moviePagingLiveData: MutableLiveData<PagingData<Movie>> = MutableLiveData()
    val moviePagingLiveData: LiveData<PagingData<Movie>> get() = _moviePagingLiveData


    fun getMovies(movieType: Int) {
        pagingSource.setCurrentMovieType(movieType)
        add(Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            pagingSourceFactory = { pagingSource }
        ).flowable.subscribe {
            _moviePagingLiveData.value = it
        })
    }
}