package com.caner.data.di

import android.content.Context
import android.content.SharedPreferences
import com.caner.data.local.PrefKeys
import com.caner.data.local.PrefStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PrefKeys.SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): PrefStore {
        return PrefStore(context)
    }
}
