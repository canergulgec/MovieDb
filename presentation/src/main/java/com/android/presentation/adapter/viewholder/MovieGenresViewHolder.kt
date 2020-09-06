package com.android.presentation.adapter.viewholder

import com.android.base.BaseViewHolder
import com.android.base.ext.use
import com.android.data.model.remote.MovieGenre
import com.android.presentation.databinding.RecyclerviewGenreBinding

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