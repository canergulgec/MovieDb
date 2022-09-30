package com.caner.data.repository

import com.caner.data.api.SearchApi
import com.caner.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val apiService: SearchApi
) : SearchRepository {

    override suspend fun searchMovie(query: String?) = apiService.searchMovie(query)
}
