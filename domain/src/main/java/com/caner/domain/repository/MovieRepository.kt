package com.caner.domain.repository

import androidx.paging.PagingData
import com.caner.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(movieType: Int): Flow<PagingData<Movie>>
}
