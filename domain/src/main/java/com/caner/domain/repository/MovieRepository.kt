package com.caner.domain.repository

import androidx.paging.PagingData
import com.caner.domain.model.remote.MovieResponseItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(path: String): Flow<PagingData<MovieResponseItem>>
}
