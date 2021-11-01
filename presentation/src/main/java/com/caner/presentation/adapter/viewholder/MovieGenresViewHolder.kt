package com.caner.presentation.adapter.viewholder

import com.android.data.model.remote.MovieGenre
import com.android.presentation.databinding.RecyclerviewGenreBinding
import com.caner.core.base.BaseViewHolder
import com.caner.core.extension.use

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
