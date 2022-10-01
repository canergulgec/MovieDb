package com.caner.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caner.data.api.MovieApi
import com.caner.domain.utils.HttpParams
import com.caner.domain.model.remote.MovieResponseItem
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val service: MovieApi,
    private val path: String
) : PagingSource<Int, MovieResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseItem> {
        val page = params.key ?: HttpParams.MOVIE_STARTING_PAGE_INDEX

        return try {
            service.getMovies(path = path, page = page).run {
                LoadResult.Page(
                    data = this.results,
                    prevKey = if (page == HttpParams.MOVIE_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (page == this.total) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
