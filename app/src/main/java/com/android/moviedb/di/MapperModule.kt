package com.android.moviedb.di

import com.android.data.mapper.MovieDetailMapper
import com.android.data.mapper.MovieMapper
import com.android.data.model.MovieDetailModel
import com.android.data.model.MovieModel
import com.android.data.model.remote.MovieDetailResponse
import com.android.data.model.remote.MovieResponse
import com.android.data.mapper.Mapper
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
