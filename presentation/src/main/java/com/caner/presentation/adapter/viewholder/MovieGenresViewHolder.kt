package com.caner.presentation.adapter.viewholder

import com.caner.data.model.remote.MovieGenre
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use
import com.caner.presentation.databinding.RecyclerviewGenreBinding

class MovieGenresViewHolder constructor(
    private val genreBinding: RecyclerviewGenreBinding
) : BaseViewHolder<MovieGenre, RecyclerviewGenreBinding>(genreBinding) {

    override fun bind() {
        getRowItem()?.apply {
            genreBinding.use {
                genre = getRowItem()
            }
        }
    }
}
