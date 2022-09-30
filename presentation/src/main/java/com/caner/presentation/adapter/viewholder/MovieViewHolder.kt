package com.caner.presentation.adapter.viewholder

import com.caner.domain.model.Movie
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use
import com.caner.presentation.databinding.ItemMovieBinding

class MovieViewHolder constructor(
    private val movieBinding: ItemMovieBinding,
    clickFunc: (Int) -> Unit
) : BaseViewHolder<Movie, ItemMovieBinding>(movieBinding) {

    init {
        itemView.setOnClickListener {
            clickFunc.invoke(getRowItem()?.movieId ?: 0)
        }
    }

    override fun bind() {
        getRowItem()?.apply {
            movieBinding.use {
                movie = getRowItem()
            }
        }
    }
}
