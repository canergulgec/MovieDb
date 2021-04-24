package com.android.test.test

import android.util.Log
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
import com.android.moviedb.ui.movie.MovieFragment
import com.android.test.screen.MovieScreen
import com.android.test.utils.OkHttpProvider
import com.android.test.utils.dispatcherWithCustomBody
import com.android.test.utils.launchFragmentInHiltContainer
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
class MovieFragmentTest {

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
        mockWebServer.dispatcher = dispatcherWithCustomBody()

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

    @Test
    fun recyclerview_second_item_should_be_visible() {
        onScreen<MovieScreen> {
            recycler {
                childAt<MovieScreen.Item>(2) {
                    name { isVisible() }
                }
            }
        }
    }

    @Test
    fun recyclerview_should_scroll_to_ninth_item_then_click_on_it() {
        onScreen<MovieScreen> {
            recycler {
                scrollTo(9)
                childAt<MovieScreen.Item>(9) {
                    click()
                }
            }
        }
    }

    @After
    fun cleanup() {
        mockWebServer.close()
    }
}
