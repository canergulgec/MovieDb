package com.android.moviedb.di

import android.content.Context
import android.content.SharedPreferences
import com.android.data.Constants
import com.android.data.local.PrefStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): PrefStore {
        return PrefStore(context)
    }
}
