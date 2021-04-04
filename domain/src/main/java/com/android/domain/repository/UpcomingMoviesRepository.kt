package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import com.android.domain.paginginterface.MoviePagingRepository

interface UpcomingMoviesRepository : MoviePagingRepository {
    override suspend fun getMovies(params: HashMap<String, Any>?): MoviesResponse
}
