package com.caner.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caner.data.api.MovieApi
import com.caner.core.Constants
import com.caner.data.model.remote.MovieResponseItem
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val service: MovieApi,
    private val type: Int
) : PagingSource<Int, MovieResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseItem> {
        val page = params.key ?: Constants.MOVIE_STARTING_PAGE_INDEX

        return try {
            val apiRequest = if (type == Constants.NOW_PLAYING_MOVIES) {
                service.getNowPlayingMovies(getParams(page))
            } else {
                service.getUpcomingMovies(getParams(page))
            }
            apiRequest.run {
                LoadResult.Page(
                    data = this.results,
                    prevKey = if (page == Constants.MOVIE_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (page == this.total) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun getParams(page: Int): HashMap<String, Any> {
        return object : LinkedHashMap<String, Any>() {
            init {
                put(Constants.PAGE, page)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
