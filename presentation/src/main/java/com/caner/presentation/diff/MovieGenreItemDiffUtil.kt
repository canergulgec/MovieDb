package com.caner.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.caner.data.model.remote.MovieGenre

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
