package com.android.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.base.BaseRecyclerAdapter
import com.android.data.diff.MovieGenreItemDiffUtil
import com.android.data.model.remote.MovieGenre
import com.android.presentation.adapter.viewholder.MovieGenresViewHolder
import com.android.presentation.databinding.RecyclerviewGenreBinding

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