package com.android.domain.repository

import com.android.data.mapper.MovieMapper
import com.android.domain.api.SearchApi
import com.android.domain.extension.filterMapperResponse
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
