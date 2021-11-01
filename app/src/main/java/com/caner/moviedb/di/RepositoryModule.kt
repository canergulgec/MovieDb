package com.caner.moviedb.di

import com.caner.domain.repository.MovieDetailRepository
import com.caner.domain.repository.MovieDetailRepositoryImp
import com.caner.domain.repository.MovieRepository
import com.caner.domain.repository.MovieRepositoryImp
import com.caner.domain.repository.NewTokenRepository
import com.caner.domain.repository.NewTokenRepositoryImp
import com.caner.domain.repository.SearchRepository
import com.caner.domain.repository.SearchRepositoryImp
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
    abstract fun provideNewTokenRepositoryImp(repositoryImp: NewTokenRepositoryImp): NewTokenRepository

    @Binds
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    abstract fun provideSearchRepositoryImp(repositoryImp: SearchRepositoryImp): SearchRepository
}
