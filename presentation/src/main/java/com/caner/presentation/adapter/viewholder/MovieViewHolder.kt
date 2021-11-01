package com.caner.presentation.adapter.viewholder

import com.android.data.model.Movie
import com.android.presentation.databinding.RecyclerviewMovieBinding
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use

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
