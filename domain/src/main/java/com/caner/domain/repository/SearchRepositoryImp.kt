package com.caner.domain.repository

import com.android.data.mapper.MovieMapper
import com.caner.domain.api.SearchApi
import com.caner.domain.extension.filterMapperResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val apiService: SearchApi,
    private val movieMapper: MovieMapper
) : SearchRepository {

    override fun searchMovie(query: String?) = flow {
        val data = apiService.searchMovie(query)
        emit(data.filterMapperResponse(movieMapper))
    }
}
