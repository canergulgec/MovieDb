package com.android.test.utils

import com.android.data.model.Image
import com.android.data.model.Movie
import com.android.data.model.MovieDetailModel
import com.android.data.model.MovieModel
import com.android.data.model.remote.MovieResponseItem
import com.android.data.model.remote.MoviesResponse
import com.android.data.model.remote.TokenResponse
import java.text.SimpleDateFormat
import java.util.Locale

class TestDataGenerator {

    companion object {
        fun generateMovies(): MoviesResponse {
            return MoviesResponse(
                total = 10,
                page = 1,
                listOf(
                    MovieResponseItem(
                        1,
                        20353.424,
                        false,
                        "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        false,
                        "/jMWkd0fuwbG39eJpzycJzPWMCww.jpg",
                        "",
                        "Godzilla vs. Kong",
                        "Godzilla vs. Kong",
                        8.5,
                        "",
                        "2021-03-24"
                    )
                )
            )
        }

        fun generateMovieDetail(): MovieDetailModel {
            return MovieDetailModel(
                1,
                title = "asd",
                backdrop = Image("/jMWkd0fuwbG39eJpzycJzPWMCww.jpg"),
                poster = Image("/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg")
            )
        }

        fun generateSearchData(): MovieModel {
            return MovieModel(
                10,
                page = 1,
                listOf(
                    Movie(
                        1,
                        20353.424,
                        false,
                        Image("/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"),
                        false,
                        Image("/jMWkd0fuwbG39eJpzycJzPWMCww.jpg"),
                        "",
                        "Godzilla vs. Kong",
                        "Godzilla vs. Kong",
                        8.5,
                        "",
                        SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                            "2021-03-24"
                        )
                    )
                )
            )
        }

        fun generateTokenResponse(): TokenResponse {
            return TokenResponse(
                true,
                "xyz"
            )
        }
    }
}
