package com.android.data.mapper

import com.android.data.model.MovieImage
import com.android.data.model.Movie
import com.android.data.model.MovieModel
import com.android.data.model.remote.MovieResponseItem
import com.android.data.model.remote.MovieResponse
import com.caner.common.Mapper
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieResponse, MovieModel> {

    override fun from(e: MovieModel): MovieResponse {
        return with(e) {
            MovieResponse(
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
                            SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(
                                date
                            )
                        }
                    )
                }
            )
        }
    }

    override fun to(t: MovieResponse): MovieModel {
        return with(t) {
            MovieModel(
                total = total,
                page = page,
                movies = results.map {
                    Movie(
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { path -> MovieImage(path) },
                        it.adult,
                        it.backdropPath?.let { path -> MovieImage(path) },
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                val parsedDate =
                                    SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                                        date
                                    ) ?: Date()
                                SimpleDateFormat("yyyy", Locale.getDefault()).format(parsedDate)
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
