package com.caner.domain.repository

import com.caner.core.extension.toResource
import com.caner.core.network.Resource
import com.caner.data.model.remote.MovieResponse
import com.caner.domain.api.SearchApi
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val apiService: SearchApi
) : SearchRepository {

    override suspend fun searchMovie(query: String?): Resource<MovieResponse> {
        val data = apiService.searchMovie(query)
        return data.toResource()
    }
}
