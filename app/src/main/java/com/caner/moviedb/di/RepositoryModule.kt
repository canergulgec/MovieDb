package com.caner.moviedb.di

import com.caner.data.repository.MovieDetailRepository
import com.caner.data.repository.MovieDetailRepositoryImp
import com.caner.data.repository.MovieRepository
import com.caner.data.repository.MovieRepositoryImp
import com.caner.data.repository.SearchRepository
import com.caner.data.repository.SearchRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMoviesRepositoryImp(repositoryImp: MovieRepositoryImp): MovieRepository

    @Binds
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    abstract fun provideSearchRepositoryImp(repositoryImp: SearchRepositoryImp): SearchRepository
}
