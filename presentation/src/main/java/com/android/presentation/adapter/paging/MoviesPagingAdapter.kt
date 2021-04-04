package com.android.presentation.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.base.BasePagingAdapter
import com.android.data.model.Movie
import com.android.presentation.adapter.viewholder.MovieViewHolder
import com.android.presentation.databinding.RecyclerviewMovieBinding
import com.android.presentation.diff.MovieItemDiffUtil

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
