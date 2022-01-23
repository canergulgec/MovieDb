package com.caner.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.core.network.HttpParams
import com.caner.data.api.MovieApi
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: MovieApi
) : MovieRepository {

    override fun getMovies(path: String) =
        Pager(
            config = PagingConfig(pageSize = HttpParams.NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviesPagingSource(service = apiService, path) }
        ).flow
}
