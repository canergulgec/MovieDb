package com.android.presentation.adapter.viewholder

import com.android.base.BaseViewHolder
import com.caner.common.ext.loadRoundedCornerImage
import com.caner.common.ext.use
import com.android.data.model.Movie
import com.android.presentation.databinding.RecyclerviewMovieBinding

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
                movieRatingBar.rating = (voteAverage / 2).toFloat()
                poster?.medium?.let { movieIv.loadRoundedCornerImage(it, 12f) }
            }
        }
    }
}