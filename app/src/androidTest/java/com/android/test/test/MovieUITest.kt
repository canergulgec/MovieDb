package com.android.test.test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.filters.LargeTest
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.android.moviedb.ui.main.MainActivity
import com.android.test.utils.FileReader
import com.android.test.utils.OkHttpProvider
import com.android.test.screen.MovieScreen
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test


@LargeTest
class MovieUITest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            ))
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun recyclerview_second_item_should_be_visible() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readTestResourceFile("movie_success_response.json"))
            }
        }

        onScreen<MovieScreen> {
            recycler {
                childAt<MovieScreen.Item>(2) {
                    name { isVisible() }
                }
            }
        }
    }

    @Test
    fun recyclerview_second_item_click_should_open_detail_page() {
        onScreen<MovieScreen> {
            recycler {
                Thread.sleep(4000)
                scrollTo(10)
                childAt<MovieScreen.Item>(10){
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