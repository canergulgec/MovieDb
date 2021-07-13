package com.android.data.mapper

import com.android.data.model.MovieImage
import com.android.data.model.MovieDetailModel
import com.android.data.model.remote.MovieDetailResponse
import com.caner.common.Mapper
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
                poster_path?.let { path -> MovieImage(path) },
                backdrop_path?.let { path -> MovieImage(path) },
                genres,
                title,
                overview,
                imdb_id,
                runtime,
                vote_average,
                vote_count,
                release_date?.let { date ->
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
