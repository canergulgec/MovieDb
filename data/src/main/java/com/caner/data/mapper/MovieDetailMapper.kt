package com.caner.data.mapper

import com.caner.data.model.MovieImage
import com.caner.data.model.MovieDetailModel
import com.caner.data.model.remote.MovieDetailResponse
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() :
    Mapper<MovieDetailResponse, MovieDetailModel> {

    override fun from(e: MovieDetailModel): MovieDetailResponse {
        return with(e) {
            MovieDetailResponse(
                movieId,
                popularity,
                video,
                adult,
                backdrop?.original,
                poster?.original,
                genres,
                title,
                overview,
                imdbId,
                runtime,
                voteAverage,
                voteCount,
                releaseDate?.let { date ->
                    SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(date)
                }
            )
        }
    }

    override fun to(t: MovieDetailResponse): MovieDetailModel {
        return with(t) {
            MovieDetailModel(
                id,
                popularity,
                video,
                adult,
                posterPath?.let { path -> MovieImage(path) },
                backdropPath?.let { path -> MovieImage(path) },
                genres,
                title,
                overview,
                imdbId,
                runtime,
                voteAverage,
                voteCount,
                releaseDate?.let { date ->
                    if (date.isNotEmpty()) {
                        SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                            date
                        )
                    } else {
                        null
                    }
                }
            )
        }
    }
}
