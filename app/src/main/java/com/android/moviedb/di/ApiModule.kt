package com.android.moviedb.di

import com.android.domain.api.NewTokenApi
import com.android.domain.api.SearchApi
import com.android.domain.api.MovieApi
import com.android.domain.api.MovieDetailApi
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
