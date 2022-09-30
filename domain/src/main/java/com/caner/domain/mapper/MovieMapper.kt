package com.caner.domain.mapper

import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage
import com.caner.domain.model.MovieModel
import com.caner.domain.model.remote.MovieResponse
import com.caner.domain.model.remote.MovieResponseItem
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieResponse, MovieModel> {

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

    fun toMovie(t: MovieResponseItem): Movie {
        return with(t) {
            Movie(
                id,
                popularity,
                video,
                posterPath?.let { path -> MovieImage(path) },
                adult,
                backdropPath?.let { path -> MovieImage(path) },
                originalLanguage,
                originalTitle,
                title,
                voteAverage,
                overview,
                releaseDate?.let { date ->
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
    }
}
