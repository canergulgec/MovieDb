package com.android.presentation.adapter.viewholder

import com.android.base.BaseViewHolder
import com.android.data.model.Movie
import com.android.presentation.databinding.RecyclerviewMovieSearchBinding
import com.caner.common.extension.use

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
