package com.android.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.base.BaseRecyclerAdapter
import com.android.presentation.diff.MovieImagesDiffUtil
import com.android.data.model.remote.BackdropItem
import com.android.presentation.adapter.viewholder.MovieImagesViewHolder
import com.android.presentation.databinding.RecyclerviewMovieImageBinding

class MovieImagesAdapter :
    BaseRecyclerAdapter<BackdropItem, RecyclerviewMovieImageBinding, MovieImagesViewHolder>(
        MovieImagesDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImagesViewHolder {
        val view = RecyclerviewMovieImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieImagesViewHolder(view)
    }
}