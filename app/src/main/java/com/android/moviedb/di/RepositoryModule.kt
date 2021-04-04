package com.android.moviedb.di

import com.android.domain.repository.NowPlayingMoviesRepository
import com.android.domain.repository.NowPlayingMoviesRepositoryImp
import com.android.domain.repository.UpcomingMoviesRepository
import com.android.domain.repository.UpcomingMoviesRepositoryImp
import com.android.domain.repository.NewTokenRepositoryImp
import com.android.domain.repository.NewTokenRepository
import com.android.domain.repository.MovieDetailRepositoryImp
import com.android.domain.repository.MovieDetailRepository
import com.android.domain.repository.SearchRepositoryImp
import com.android.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNowPlayingMoviesRepositoryImp(repositoryImp: NowPlayingMoviesRepositoryImp): NowPlayingMoviesRepository

    @Binds
    abstract fun provideUpcomingMoviesRepositoryImp(repositoryImp: UpcomingMoviesRepositoryImp): UpcomingMoviesRepository

    @Binds
    abstract fun provideNewTokenRepositoryImp(repositoryImp: NewTokenRepositoryImp): NewTokenRepository

    @Binds
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    abstract fun provideSearchRepositoryImp(repositoryImp: SearchRepositoryImp): SearchRepository
}