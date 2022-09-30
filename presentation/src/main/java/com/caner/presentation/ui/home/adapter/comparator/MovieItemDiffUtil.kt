package com.caner.presentation.ui.home.adapter.comparator

import androidx.recyclerview.widget.DiffUtil
import com.caner.domain.model.Movie

class MovieItemDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}
