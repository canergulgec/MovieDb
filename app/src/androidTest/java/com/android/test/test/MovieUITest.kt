package com.android.test.test

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import com.agoda.kakao.Kakao
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.android.moviedb.ui.main.MainActivity
import com.android.test.screen.MovieScreen
import org.junit.After
import org.junit.Before
import org.junit.Test


@LargeTest
class MovieUITest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        Kakao {
            intercept {
                onViewInteraction { // Intercepting calls on ViewInteraction classes across whole runtime
                    onPerform { interaction, action -> // Intercept perform() call
                        Log.d("KAKAO", "$interaction is performing $action")
                    }
                }
            }
        }

        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun recyclerview_second_item_should_be_visible() {
        onScreen<MovieScreen> {
            recycler {
                Thread.sleep(4000)
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
        activityScenario.close()
    }
}