package com.caner.presentation.adapter.viewholder

import com.caner.data.model.Movie
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use
import com.caner.presentation.databinding.ItemMovieBinding

class MovieViewHolder constructor(
    private val movieBinding: ItemMovieBinding,
    clickFunc: (Movie?) -> Unit
) : BaseViewHolder<Movie, ItemMovieBinding>(movieBinding) {

    init {
        itemView.setOnClickListener {
            clickFunc.invoke(getRowItem())
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
