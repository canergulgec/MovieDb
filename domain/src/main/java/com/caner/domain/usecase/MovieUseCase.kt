package com.caner.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.model.Movie
import com.caner.domain.pagingsource.MoviesPagingSource
import com.caner.domain.repository.MovieRepository
import com.caner.domain.utils.HttpParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: MovieMapper
) {
    fun getMovies(path: String): Flow<PagingData<Movie>> {
        val moviePager = Pager(
            config = PagingConfig(pageSize = HttpParams.NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(repository = movieRepository, path = path) }
        ).flow

        return moviePager.map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
    }
}
