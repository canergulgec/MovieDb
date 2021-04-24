package com.android.moviedb.di

import android.content.Context
import com.android.moviedb.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
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
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Named("AuthInterceptor")
    internal fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url: HttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    fun provideOkHttpAuth(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("AuthInterceptor") authInterceptor: Interceptor,
        stethoInterceptor: StethoInterceptor,
        @ApplicationContext appContext: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(
                FlipperOkhttpInterceptor(
                    AndroidFlipperClient.getInstance(appContext).getPlugin(NetworkFlipperPlugin.ID)
                )
            )
            .addNetworkInterceptor(stethoInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
