package com.caner.data.repository

import com.caner.data.api.MovieApi
import com.caner.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: MovieApi
) : MovieRepository {

    override suspend fun getMovies(path: String, page: Int) = apiService.getMovies(path, page)
}
