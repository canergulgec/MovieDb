package com.caner.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caner.data.mapper.MovieMapper
import com.caner.data.model.Movie
import com.caner.domain.api.MovieApi
import com.caner.data.Constants
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    private val movieType: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: Constants.MOVIE_STARTING_PAGE_INDEX

        return try {
            val apiRequest = if (movieType == Constants.NOW_PLAYING_MOVIES) {
                movieApi.getNowPlayingMovies(getParams(page))
            } else {
                movieApi.getUpcomingMovies(getParams(page))
            }
            apiRequest.run {
                val data = movieMapper.to(this)
                LoadResult.Page(
                    data = data.movies,
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
