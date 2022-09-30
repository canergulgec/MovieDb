package com.caner.domain.usecase

import androidx.paging.map
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.repository.MovieRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {
    fun getMovies(path: String) = repository.getMovies(path = path)
        .map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
}
