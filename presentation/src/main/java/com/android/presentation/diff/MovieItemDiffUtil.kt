package com.android.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.android.data.model.Movie

class MovieItemDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean =
        oldItem == newItem
}