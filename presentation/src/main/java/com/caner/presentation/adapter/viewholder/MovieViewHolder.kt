package com.caner.presentation.adapter.viewholder

import com.android.data.model.Movie
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use
import com.caner.presentation.databinding.RecyclerviewMovieBinding

class MovieViewHolder constructor(
    private val movieBinding: RecyclerviewMovieBinding,
    clickFunc: (Movie?) -> Unit
) : BaseViewHolder<Movie, RecyclerviewMovieBinding>(movieBinding) {

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
