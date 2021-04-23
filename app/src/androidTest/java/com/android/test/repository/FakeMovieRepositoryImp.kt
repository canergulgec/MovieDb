package com.android.test.repository

import com.android.data.model.remote.MoviesResponse
import com.android.domain.repository.MovieRepository
import com.android.test.utils.TestDataGenerator
import javax.inject.Inject

/**
 * Fake repository for UI testing
 */
class FakeMovieRepositoryImp @Inject constructor() : MovieRepository {

    override suspend fun getNowPlayingMovies(params: HashMap<String, Any>?): MoviesResponse {
        return TestDataGenerator.generateMovies()
    }

    override suspend fun getUpcomingMovies(params: HashMap<String, Any>?): MoviesResponse {
        return TestDataGenerator.generateMovies()
    }
}
