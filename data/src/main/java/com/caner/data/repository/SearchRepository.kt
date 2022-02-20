package com.caner.data.repository

import com.caner.data.model.remote.MovieResponse

interface SearchRepository {
    suspend fun searchMovie(query: String?): MovieResponse
}
