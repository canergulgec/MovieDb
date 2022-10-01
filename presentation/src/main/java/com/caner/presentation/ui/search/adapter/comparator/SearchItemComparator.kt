package com.caner.presentation.ui.search.adapter.comparator

import androidx.recyclerview.widget.DiffUtil
import com.caner.domain.model.Movie

object SearchItemComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}
