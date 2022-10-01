package com.caner.presentation.ui.detail.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.caner.domain.model.remote.MovieGenre
import com.caner.presentation.utils.extension.use
import com.caner.presentation.databinding.ItemGenreBinding

class MovieGenresViewHolder(private val binding: ItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieGenre) {
        binding.use {
            genre = item
        }
    }
}
