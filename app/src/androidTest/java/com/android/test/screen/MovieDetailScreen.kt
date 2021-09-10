package com.android.test.screen

import android.view.View
import com.android.moviedb.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
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
