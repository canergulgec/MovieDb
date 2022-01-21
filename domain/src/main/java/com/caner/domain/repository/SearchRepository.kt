package com.caner.domain.repository

import com.caner.core.network.Resource
import com.caner.data.model.remote.MovieResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): Resource<MovieResponse>
}
