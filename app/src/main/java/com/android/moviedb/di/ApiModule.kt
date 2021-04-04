package com.android.moviedb.di

import com.android.domain.api.MovieDetailApi
import com.android.domain.api.NewTokenApi
import com.android.domain.api.NowPlayingMoviesApi
import com.android.domain.api.UpcomingMoviesApi
import com.android.domain.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideNowPlayingMoviesApiService(retrofit: Retrofit): NowPlayingMoviesApi {
        return retrofit.create(NowPlayingMoviesApi::class.java)
    }

    @Provides
    fun provideUpcomingMoviesApiService(retrofit: Retrofit): UpcomingMoviesApi {
        return retrofit.create(UpcomingMoviesApi::class.java)
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