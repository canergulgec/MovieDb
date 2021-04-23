package com.android.moviedb.di

import com.android.domain.repository.MovieDetailRepository
import com.android.domain.repository.MovieDetailRepositoryImp
import com.android.domain.repository.MovieRepository
import com.android.domain.repository.MovieRepositoryImp
import com.android.domain.repository.NewTokenRepository
import com.android.domain.repository.NewTokenRepositoryImp
import com.android.domain.repository.SearchRepository
import com.android.domain.repository.SearchRepositoryImp
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
