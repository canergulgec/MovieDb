package com.caner.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.core.constants.HttpParams
import com.caner.data.api.MovieApi
import com.caner.data.pagingsource.MoviesPagingSource
import com.caner.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: MovieApi
) : MovieRepository {

    override fun getMovies(path: String) =
        Pager(
            config = PagingConfig(pageSize = HttpParams.NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(service = apiService, path) }
        ).flow
}
