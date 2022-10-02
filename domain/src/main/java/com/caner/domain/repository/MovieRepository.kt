package com.caner.domain.repository

import com.caner.domain.model.remote.MovieResponse

interface MovieRepository {

    suspend fun getMovies(path: String, page: Int): MovieResponse
}
