package com.caner.domain.repository

import com.caner.data.model.MovieModel
import com.caner.domain.viewstate.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovie(query: String?): Flow<Resource<MovieModel>>
}
