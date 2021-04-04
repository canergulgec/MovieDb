package com.android.domain.paginginterface

import com.android.data.model.remote.MoviesResponse

interface MoviePagingRepository {
    suspend fun getMovies(params: HashMap<String, Any>?): MoviesResponse
}
