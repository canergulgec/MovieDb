package com.android.test.repository

import com.android.data.model.MovieModel
import com.android.domain.repository.SearchRepository
import com.android.test.utils.TestDataGenerator
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeSearchRepositoryImp @Inject constructor() : SearchRepository {

    override fun searchMovie(query: String?): Flow<Resource<MovieModel>> {
        return flow { emit(Resource.Success(TestDataGenerator.generateSearchData())) }
    }
}
