package com.android.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.data.model.Movie
import com.android.presentation.adapter.viewholder.MovieSearchViewHolder
import com.android.presentation.databinding.RecyclerviewMovieSearchBinding
import com.android.presentation.diff.MovieItemDiffUtil
import com.caner.core.base.BaseRecyclerAdapter

class MovieSearchAdapter(private val clickFunc: (Movie?) -> Unit) :
    BaseRecyclerAdapter<Movie, RecyclerviewMovieSearchBinding, MovieSearchViewHolder>(
        MovieItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val view =
            RecyclerviewMovieSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MovieSearchViewHolder(view, clickFunc = clickFunc)
    }
}
