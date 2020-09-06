package com.android.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.base.BaseRecyclerAdapter
import com.android.data.diff.MovieVideosDiffUtil
import com.android.data.model.remote.VideoItem
import com.android.presentation.adapter.viewholder.MovieVideosViewHolder
import com.android.presentation.databinding.RecyclerviewMovieVideoBinding

class MovieVideosAdapter :
    BaseRecyclerAdapter<VideoItem, RecyclerviewMovieVideoBinding, MovieVideosViewHolder>(
        MovieVideosDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideosViewHolder {
        val binding = RecyclerviewMovieVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieVideosViewHolder(binding)
    }
}