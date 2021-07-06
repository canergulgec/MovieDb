package com.android.domain.repository

import androidx.paging.PagingData
import com.android.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}
