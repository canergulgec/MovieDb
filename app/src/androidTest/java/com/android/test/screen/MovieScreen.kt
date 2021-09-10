package com.android.test.screen

import android.view.View
import com.android.moviedb.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

class MovieScreen : Screen<MovieScreen>() {
    val moviesRv = KRecyclerView(
        { withId(R.id.moviesRv) },
        itemTypeBuilder = {
            itemType(MovieScreen::Item)
        }
    )

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val moviePoster: KImageView = KImageView(parent) { withId(R.id.movieIv) }
        val movieName: KTextView = KTextView(parent) { withId(R.id.movieNameTv) }
    }
}
