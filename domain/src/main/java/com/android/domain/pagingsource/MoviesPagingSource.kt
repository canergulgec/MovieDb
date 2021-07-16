package com.android.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.data.mapper.MovieMapper
import com.android.data.model.Movie
import com.android.domain.api.MovieApi
import com.caner.common.Constants
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    private val movieType: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val apiRequest = if (movieType == Constants.NOW_PLAYING_MOVIES) {
            movieApi.getNowPlayingMovies(getParams(page))
        } else {
            movieApi.getUpcomingMovies(getParams(page))
        }

        return try {
            apiRequest.run {
                val data = movieMapper.to(this)
                LoadResult.Page(
                    data = data.movies,
                    prevKey = null,
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
        return state.anchorPosition
    }
}
