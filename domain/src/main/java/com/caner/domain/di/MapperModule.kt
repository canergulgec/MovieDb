package com.caner.domain.di

import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.mapper.MovieMapper
import com.caner.data.model.MovieDetailModel
import com.caner.data.model.MovieModel
import com.caner.data.model.remote.MovieDetailResponse
import com.caner.data.model.remote.MovieResponse
import com.caner.domain.mapper.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsMovieMapper(mapper: MovieMapper): Mapper<MovieResponse, MovieModel>

    @Binds
    abstract fun bindsMovieDetailMapper(mapper: MovieDetailMapper): Mapper<MovieDetailResponse, MovieDetailModel>
}