package com.caner.domain.repository

import com.caner.domain.model.remote.MovieResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MovieResponse
}
