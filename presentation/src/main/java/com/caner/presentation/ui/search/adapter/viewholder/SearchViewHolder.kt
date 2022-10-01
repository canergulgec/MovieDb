package com.caner.presentation.ui.search.adapter.viewholder

import com.caner.domain.model.Movie
import com.caner.core.BaseViewHolder
import com.caner.presentation.utils.extension.use
import com.caner.presentation.databinding.ItemMovieSearchBinding

class SearchViewHolder constructor(
    private val searchBinding: ItemMovieSearchBinding,
    clickFunc: (Int) -> Unit
) : BaseViewHolder<Movie, ItemMovieSearchBinding>(searchBinding) {

    init {
        itemView.setOnClickListener {
            clickFunc.invoke(getRowItem()?.movieId ?: 0)
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
