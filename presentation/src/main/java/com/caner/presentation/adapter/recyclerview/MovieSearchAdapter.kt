package com.caner.presentation.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.data.model.Movie
import com.caner.presentation.adapter.viewholder.MovieSearchViewHolder
import com.caner.presentation.adapter.diff.MovieItemDiffUtil
import com.caner.core.base.BaseRecyclerAdapter
import com.caner.presentation.databinding.ItemMovieSearchBinding

class MovieSearchAdapter(private val onClick: (Movie?) -> Unit) :
    BaseRecyclerAdapter<Movie, ItemMovieSearchBinding, MovieSearchViewHolder>(
        MovieItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val view =
            ItemMovieSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MovieSearchViewHolder(view, clickFunc = onClick)
    }
}
