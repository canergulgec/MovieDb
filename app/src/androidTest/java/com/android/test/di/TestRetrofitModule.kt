package com.android.test.di

import com.caner.moviedb.BuildConfig
import com.caner.moviedb.di.NetworkModule
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestRetrofitModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return "http://localhost:8080"
    }

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Provides
    fun provideConverterFactory(builder: GsonBuilder): Converter.Factory {
        return GsonConverterFactory.create(builder.create())
    }

    @Provides
    fun provideOkHttpAuth(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        gsonConverterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
