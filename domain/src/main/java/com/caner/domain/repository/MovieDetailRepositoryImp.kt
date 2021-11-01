package com.caner.domain.repository

import com.caner.data.model.MovieDetailModel
import com.caner.data.model.remote.MovieDetailResponse
import com.caner.domain.api.MovieDetailApi
import com.caner.data.mapper.Mapper
import com.caner.domain.extension.filterMapperResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi,
    private val movieDetailMapper: Mapper<MovieDetailResponse, MovieDetailModel>,
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?) = flow {
        val data = apiService.getMovieDetail(movieId)
        emit(data.filterMapperResponse(movieDetailMapper))
    }
}
