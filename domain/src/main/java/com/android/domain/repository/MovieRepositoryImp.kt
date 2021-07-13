package com.android.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.data.mapper.MovieMapper
import com.android.domain.api.MovieApi
import com.android.domain.pagingsource.MoviesPagingSource
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getMovies() =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(movieApi, movieMapper) }
        ).flow
}

