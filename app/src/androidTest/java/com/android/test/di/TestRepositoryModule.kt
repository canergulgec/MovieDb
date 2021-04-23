package com.android.test.di

import com.android.domain.repository.MovieDetailRepository
import com.android.domain.repository.MovieRepository
import com.android.domain.repository.NewTokenRepository
import com.android.domain.repository.SearchRepository
import com.android.moviedb.di.RepositoryModule
import com.android.test.repository.FakeDetailRepositoryImp
import com.android.test.repository.FakeMovieRepositoryImp
import com.android.test.repository.FakeNewTokenRepositoryImp
import com.android.test.repository.FakeSearchRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.testing.TestInstallIn

/**
 * Repository binding to use in tests.
 *
 */
@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class TestRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieRepository(repository: FakeMovieRepositoryImp): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun provideMovieDetailRepository(repository: FakeDetailRepositoryImp): MovieDetailRepository

    @Binds
    @ViewModelScoped
    abstract fun provideNewTokenRepository(repository: FakeNewTokenRepositoryImp): NewTokenRepository

    @Binds
    @ViewModelScoped
    abstract fun provideSearchRepository(repository: FakeSearchRepositoryImp): SearchRepository
}
