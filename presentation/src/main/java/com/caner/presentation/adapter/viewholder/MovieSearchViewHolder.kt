package com.caner.presentation.adapter.viewholder

import com.android.data.model.Movie
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use
import com.caner.presentation.databinding.RecyclerviewMovieSearchBinding

class MovieSearchViewHolder constructor(
    private val searchBinding: RecyclerviewMovieSearchBinding,
    clickFunc: (Movie?) -> Unit
) : BaseViewHolder<Movie, RecyclerviewMovieSearchBinding>(searchBinding) {

    init {
        itemView.setOnClickListener {
            clickFunc.invoke(getRowItem())
        }
    }

    override fun bind() {
        getRowItem()?.apply {
            searchBinding.use {
                item = getRowItem()
            }
        }
    }
}
