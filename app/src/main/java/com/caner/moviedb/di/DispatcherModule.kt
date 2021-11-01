package com.caner.moviedb.di

import com.caner.domain.qualifier.DefaultDispatcher
import com.caner.domain.qualifier.IoDispatcher
import com.caner.domain.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @DefaultDispatcher
    @Provides
    internal fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
