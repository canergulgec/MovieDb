package com.android.test.di

import com.android.moviedb.di.RetrofitModule
import com.android.test.utils.OkHttpProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
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
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        gsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpProvider.getOkHttpClient())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
