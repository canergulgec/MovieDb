package com.caner.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.data.model.remote.MovieGenre
import com.caner.presentation.adapter.viewholder.MovieGenresViewHolder
import com.android.presentation.databinding.RecyclerviewGenreBinding
import com.caner.presentation.diff.MovieGenreItemDiffUtil
import com.caner.core.base.BaseRecyclerAdapter

class MovieGenresAdapter :
    BaseRecyclerAdapter<MovieGenre, RecyclerviewGenreBinding, MovieGenresViewHolder>(
        MovieGenreItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val view =
            RecyclerviewGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(view)
    }
}
