package com.caner.presentation.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.caner.domain.model.Movie
import com.caner.presentation.utils.extension.use
import com.caner.presentation.databinding.ItemMovieBinding

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.use {
            movie = item
        }
    }
}
