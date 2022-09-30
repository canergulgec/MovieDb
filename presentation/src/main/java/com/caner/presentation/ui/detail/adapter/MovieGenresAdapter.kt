package com.caner.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.domain.model.remote.MovieGenre
import com.caner.core.base.BaseRecyclerAdapter
import com.caner.presentation.databinding.ItemGenreBinding
import com.caner.presentation.ui.detail.adapter.diffutil.MovieGenreItemDiffUtil
import com.caner.presentation.ui.detail.adapter.viewholder.MovieGenresViewHolder

class MovieGenresAdapter :
    BaseRecyclerAdapter<MovieGenre, ItemGenreBinding, MovieGenresViewHolder>(
        MovieGenreItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val view =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(view)
    }
}
