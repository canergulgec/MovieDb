package com.android.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.android.data.model.remote.MovieGenre

class MovieGenreItemDiffUtil : DiffUtil.ItemCallback<MovieGenre>() {
    override fun areItemsTheSame(
        oldItem: MovieGenre,
        newItem: MovieGenre
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieGenre,
        newItem: MovieGenre
    ): Boolean =
        oldItem == newItem
}