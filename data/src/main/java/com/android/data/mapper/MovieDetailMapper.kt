package com.android.data.mapper

import com.android.base.Mapper
import com.android.data.model.Image
import com.android.data.model.MovieDetailModel
import com.android.data.model.remote.MovieDetailResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailModel> {

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
                    SimpleDateFormat("YYYY-mm-dd", Locale.getDefault()).format(date)
                })

        }
    }

    override fun to(t: MovieDetailResponse): MovieDetailModel {
        return with(t) {
            MovieDetailModel(
                id,
                popularity,
                video,
                adult,
                backdrop_path?.let { path -> Image(path) },
                poster_path?.let { path -> Image(path) },
                genres,
                title,
                overview,
                imdb_id,
                runtime,
                vote_average,
                vote_count,
                release_date?.let { date ->
                    if (date.isNotEmpty()) {
                        SimpleDateFormat("YYYY-mm-dd", Locale.getDefault()).parse(
                            date
                        )
                    } else {
                        null
                    }
                })
        }
    }
}