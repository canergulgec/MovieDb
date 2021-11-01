package com.android.moviedb.di

import com.caner.domain.api.NewTokenApi
import com.caner.domain.api.SearchApi
import com.caner.domain.api.MovieApi
import com.caner.domain.api.MovieDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideNewTokenApiService(retrofit: Retrofit): NewTokenApi {
        return retrofit.create(NewTokenApi::class.java)
    }

    @Provides
    fun provideMovieDetailApiService(retrofit: Retrofit): MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }

    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}
