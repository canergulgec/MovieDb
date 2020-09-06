package com.android.domain.repository

import com.android.data.model.remote.MoviesResponse
import io.reactivex.Single

interface NowPlayingMoviesRepository {
    fun getNowPlayingMovies(params: HashMap<String, Any>?): Single<MoviesResponse>
}