package com.caner.moviedb.di

import com.caner.data.mapper.MovieDetailMapper
import com.caner.data.mapper.MovieMapper
import com.caner.data.model.MovieDetailModel
import com.caner.data.model.MovieModel
import com.caner.data.model.remote.MovieDetailResponse
import com.caner.data.model.remote.MovieResponse
import com.caner.data.mapper.Mapper
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
