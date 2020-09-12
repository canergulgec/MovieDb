package com.android.domain.di

import com.android.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNowPlayingMoviesRepositoryImp(repositoryImp: NowPlayingMoviesRepositoryImp): NowPlayingMoviesRepository

    @Binds
    abstract fun provideUpcomingMoviesRepositoryImp(repositoryImp: UpcomingMoviesRepositoryImp): UpcomingMoviesRepository

    @Binds
    abstract fun provideNewTokenRepositoryImp(repositoryImp: NewTokenRepositoryImp): NewTokenRepository

    @Binds
    abstract fun provideNewSessionRepositoryImp(repositoryImp: NewSessionRepositoryImp): NewSessionRepository

    @Binds
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun provideMovieImagesRepositoryImp(repositoryImp: MovieGalleryRepositoryImp): MovieGalleryRepository
}