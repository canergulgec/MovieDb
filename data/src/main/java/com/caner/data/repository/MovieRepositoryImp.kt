package com.caner.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.core.Constants
import com.caner.data.api.MovieApi
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: MovieApi
) : MovieRepository {

    override fun getMovies(movieType: Int) =
        Pager(
            config = PagingConfig(pageSize = Constants.NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviesPagingSource(service = apiService, type = movieType) }
        ).flow
}
