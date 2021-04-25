package com.android.test.test

import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.android.moviedb.R
import com.android.moviedb.ui.movie.detail.MovieDetailFragment
import com.android.test.screen.MovieDetailScreen
import com.android.test.utils.OkHttpProvider
import com.android.test.utils.detailDispatcher
import com.android.test.utils.launchFragmentInHiltContainer
import com.caner.common.Constants
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class MovieDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val mockWebServer = MockWebServer()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            )
        )
        mockWebServer.dispatcher = detailDispatcher()

        // Create a TestNavHostController
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // This needed because it throws a exception that method addObserver must be called in main thread
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        launchFragmentInHiltContainer<MovieDetailFragment>(fragmentArgs = bundleOf(Constants.MOVIE_ID to 399566)) {
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

    @Test
    fun movie_detail_should_be_visible() {
        onScreen<MovieDetailScreen> {
            moviePoster {
                isVisible()
            }

            movieTitle {
                hasAnyText()
            }

            genresRv {
                childAt<MovieDetailScreen.Item>(0) {
                    genreName {
                        isVisible()
                        hasAnyText()
                    }
                }
            }
        }
    }

    @After
    fun cleanup() {
        mockWebServer.close()
    }
}
