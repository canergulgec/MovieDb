package com.android.presentation.pagingsource

import androidx.paging.PagingSource
import com.android.data.Constants
import com.android.data.mapper.MovieMapper
import com.android.data.model.Movie
import com.android.domain.repository.NowPlayingMoviesRepository
import com.android.domain.repository.UpcomingMoviesRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val nowPlayingMoviesRepository: NowPlayingMoviesRepository,
    private val upcomingMoviesRepository: UpcomingMoviesRepository,
    private val movieMapper: MovieMapper,
) : PagingSource<Int, Movie>() {

    private var movieType = Constants.NOW_PLAYING_MOVIES

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        val repository = if (movieType == Constants.NOW_PLAYING_MOVIES) {
            nowPlayingMoviesRepository
        } else {
            upcomingMoviesRepository
        }

        return try {
            repository.getMovies(
                getParams(page)
            ).run {
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

    fun setCurrentMovieType(movieType: Int) {
        this.movieType = movieType
    }

    private fun getParams(page: Int): HashMap<String, Any>? {
        return object : LinkedHashMap<String, Any>() {
            init {
                put(Constants.page, page)
            }
        }
    }
}