package com.android.data.mapper

import com.android.base.Mapper
import com.android.data.model.*
import com.android.data.model.remote.MovieResponseItem
import com.android.data.model.remote.MoviesResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MoviesResponse, MovieModel> {

    override fun from(e: MovieModel): MoviesResponse {
        return with(e) {
            MoviesResponse(
                total = total,
                page = page,
                results = e.movies.map {
                    MovieResponseItem(
                        it.movieId,
                        it.popularity,
                        it.video,
                        it.poster?.original,
                        it.adult,
                        it.backdrop?.original,
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            SimpleDateFormat("YYYY-mm-dd", Locale.getDefault()).format(
                                date
                            )

                        }
                    )
                }
            )
        }
    }

    override fun to(t: MoviesResponse): MovieModel {
        return with(t) {
            MovieModel(
                total = total,
                page = page,
                movies = results.map {
                    Movie(
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { path -> Image(path) },
                        it.adult,
                        it.backdropPath?.let { path -> Image(path) },
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                SimpleDateFormat("YYYY-mm-dd", Locale.getDefault()).parse(
                                    date
                                )
                            } else {
                                null
                            }
                        }
                    )
                }
            )
        }
    }
}