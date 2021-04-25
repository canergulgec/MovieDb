package com.android.test.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.rating.KRatingBar
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.android.moviedb.R
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
        val movieRating: KRatingBar = KRatingBar(parent) { withId(R.id.movieRatingBar) }
    }
}
