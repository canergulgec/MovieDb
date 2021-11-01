package com.caner.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.caner.data.mapper.MovieMapper
import com.caner.domain.api.MovieApi
import com.caner.domain.pagingsource.MoviesPagingSource
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getMovies(movieType: Int) =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(movieApi, movieMapper, movieType) }
        ).flow
}
