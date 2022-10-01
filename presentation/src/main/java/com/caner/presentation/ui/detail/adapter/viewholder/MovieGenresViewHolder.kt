package com.caner.presentation.ui.detail.adapter.viewholder

import com.caner.domain.model.remote.MovieGenre
import com.caner.core.BaseViewHolder
import com.caner.presentation.utils.extension.use
import com.caner.presentation.databinding.ItemGenreBinding

class MovieGenresViewHolder constructor(
    private val genreBinding: ItemGenreBinding
) : BaseViewHolder<MovieGenre, ItemGenreBinding>(genreBinding) {

    override fun bind() {
        getRowItem()?.apply {
            genreBinding.use {
                genre = getRowItem()
            }
        }
    }
}
