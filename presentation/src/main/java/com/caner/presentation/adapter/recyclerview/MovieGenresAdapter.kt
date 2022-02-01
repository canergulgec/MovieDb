package com.caner.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.data.model.remote.MovieGenre
import com.caner.presentation.adapter.viewholder.MovieGenresViewHolder
import com.caner.presentation.diff.MovieGenreItemDiffUtil
import com.caner.core.base.BaseRecyclerAdapter
import com.caner.presentation.databinding.ItemGenreBinding

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
