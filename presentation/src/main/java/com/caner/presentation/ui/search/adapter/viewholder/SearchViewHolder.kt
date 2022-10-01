package com.caner.presentation.ui.search.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.caner.domain.model.Movie
import com.caner.presentation.utils.extension.use
import com.caner.presentation.databinding.ItemMovieSearchBinding

class SearchViewHolder constructor(private val binding: ItemMovieSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.use {
            movie = item
        }
    }
}
