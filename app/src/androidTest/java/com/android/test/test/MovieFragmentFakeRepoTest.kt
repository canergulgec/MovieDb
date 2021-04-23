package com.android.test.test

import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.agoda.kakao.screen.Screen
import com.android.moviedb.R
import com.android.moviedb.ui.movie.MovieFragment
import com.android.test.screen.MovieScreen
import com.android.test.utils.launchFragmentInHiltContainer
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class MovieFragmentFakeRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()

        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // This needed because it throws a exception that method addObserver must be called in main thread
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        launchFragmentInHiltContainer<MovieFragment> {
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(this.requireView(), navController)

                    // For workManager test
                    val config = Configuration.Builder()
                        .setMinimumLoggingLevel(Log.DEBUG)
                        .setExecutor(SynchronousExecutor())
                        .build()
                    WorkManagerTestInitHelper.initializeTestWorkManager(
                        requireContext(), config
                    )
                }
            }
        }
    }

    /**
     * Test navigation with MockK
     */
    @Test
    fun test_recycler_view_item_click() {
        Screen.onScreen<MovieScreen> {
            recycler {
                childAt<MovieScreen.Item>(0) {
                    name { isVisible() }
                    click()
                }
            }
        }

        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.movieDetailFragment)
    }
}
