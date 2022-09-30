package com.caner.presentation.ui.detail.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.caner.domain.model.remote.MovieGenre

class MovieGenreItemDiffUtil : DiffUtil.ItemCallback<MovieGenre>() {
    override fun areItemsTheSame(oldItem: MovieGenre, newItem: MovieGenre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieGenre, newItem: MovieGenre): Boolean {
        return oldItem == newItem
    }
}
