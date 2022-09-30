package com.caner.data.di

import com.caner.domain.repository.MovieDetailRepository
import com.caner.data.repository.MovieDetailRepositoryImp
import com.caner.domain.repository.MovieRepository
import com.caner.data.repository.MovieRepositoryImp
import com.caner.domain.repository.SearchRepository
import com.caner.data.repository.SearchRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesRepositoryImp(repositoryImp: MovieRepositoryImp): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    @ViewModelScoped
    abstract fun provideSearchRepositoryImp(repositoryImp: SearchRepositoryImp): SearchRepository
}
