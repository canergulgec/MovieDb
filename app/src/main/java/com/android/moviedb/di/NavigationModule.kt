package com.android.moviedb.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.moviedb.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class NavigationModule {

    @Provides
    fun provideNavController(activity: Activity): NavController =
        activity.findNavController(R.id.mainNavHostFragment)
}