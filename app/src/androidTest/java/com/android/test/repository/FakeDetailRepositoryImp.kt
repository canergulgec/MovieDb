package com.android.test.repository

import com.android.data.model.MovieDetailModel
import com.android.domain.repository.MovieDetailRepository
import com.android.test.utils.TestDataGenerator
import com.caner.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeDetailRepositoryImp @Inject constructor() : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>> {
        return flow { emit(Resource.Success(TestDataGenerator.generateMovieDetail())) }
    }
}
