package com.android.test.test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.filters.LargeTest
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.android.moviedb.ui.main.MainActivity
import com.android.test.MovieTestApp.Companion.port
import com.android.test.screen.MovieScreen
import com.android.test.utils.OkHttpProvider
import com.android.test.utils.dispatcher
import com.android.test.utils.dispatcherWithCustomBody
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@LargeTest
class MovieFragmentTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(port)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            )
        )
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun recyclerview_second_item_should_be_visible() {
        mockWebServer.dispatcher = dispatcher()
        onScreen<MovieScreen> {
            recycler {
                childAt<MovieScreen.Item>(2) {
                    name { isVisible() }
                }
            }
        }
    }

    @Test
    fun recyclerview_should_scroll_to_fifth_item_then_click_on_it() {
        mockWebServer.dispatcher = dispatcherWithCustomBody()
        onScreen<MovieScreen> {
            recycler {
                scrollTo(5)
                childAt<MovieScreen.Item>(5) {
                    click()
                }
            }
        }
    }

    @After
    fun cleanup() {
        mockWebServer.close()
        activityScenario.close()
    }
}
