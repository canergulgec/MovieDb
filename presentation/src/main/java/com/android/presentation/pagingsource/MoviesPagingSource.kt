package com.android.presentation.pagingsource

import androidx.paging.rxjava2.RxPagingSource
import com.android.data.Constants
import com.android.data.mapper.MovieMapper
import com.android.data.model.Movie
import com.android.data.model.MovieModel
import com.android.domain.usecase.NowPlayingMoviesUseCase
import com.android.domain.usecase.UpcomingMoviesUseCase
import io.reactivex.Single
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase,
    private val movieMapper: MovieMapper,
) : RxPagingSource<Int, Movie>() {

    private var movieType = Constants.NOW_PLAYING_MOVIES

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val page = params.key ?: 1

        val useCase = if (movieType == Constants.NOW_PLAYING_MOVIES) {
            nowPlayingMoviesUseCase
        } else {
            upcomingMoviesUseCase
        }

        return useCase.execute(getParams(page))
            .map { movieMapper.to(it) }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: MovieModel, page: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = data.movies,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == data.total) null else page + 1
        )
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