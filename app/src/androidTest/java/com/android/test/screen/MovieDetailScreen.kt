package com.android.test.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.android.moviedb.R
import org.hamcrest.Matcher

class MovieDetailScreen : Screen<MovieDetailScreen>() {
    val genresRv = KRecyclerView(
        { withId(R.id.movieGenresRv) },
        itemTypeBuilder = {
            itemType(MovieDetailScreen::Item)
        }
    )

    val moviePoster = KImageView { withId(R.id.moviePosterIv) }
    val movieTitle = KTextView { withId(R.id.movieTitleTv) }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val genreName: KTextView = KTextView(parent) { withId(R.id.genreNameTv) }
    }
}
