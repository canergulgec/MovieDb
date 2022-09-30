package com.caner.domain.di

import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.mapper.Mapper
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.model.MovieModel
import com.caner.domain.model.remote.MovieDetailResponse
import com.caner.domain.model.remote.MovieResponse
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
