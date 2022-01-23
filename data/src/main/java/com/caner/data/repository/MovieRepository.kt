package com.caner.data.repository

import androidx.paging.PagingData
import com.caner.data.model.remote.MovieResponseItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(movieType: Int): Flow<PagingData<MovieResponseItem>>
}
