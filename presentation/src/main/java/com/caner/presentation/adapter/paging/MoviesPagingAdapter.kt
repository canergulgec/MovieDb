package com.caner.presentation.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.data.model.Movie
import com.caner.presentation.adapter.viewholder.MovieViewHolder
import com.android.presentation.databinding.RecyclerviewMovieBinding
import com.caner.presentation.diff.MovieItemDiffUtil
import com.caner.core.base.BasePagingAdapter

class MoviesPagingAdapter(private val clickFunc: (Movie?) -> Unit) :
    BasePagingAdapter<Movie, RecyclerviewMovieBinding, MovieViewHolder>(
        MovieItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            RecyclerviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view, clickFunc = clickFunc)
    }
}
