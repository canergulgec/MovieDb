package com.caner.presentation.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.data.model.Movie
import com.caner.presentation.adapter.viewholder.MovieViewHolder
import com.caner.presentation.adapter.diff.MovieItemDiffUtil
import com.caner.core.base.BasePagingAdapter
import com.caner.presentation.databinding.ItemMovieBinding

class MoviesPagingAdapter(private val onClick: (Int) -> Unit) :
    BasePagingAdapter<Movie, ItemMovieBinding, MovieViewHolder>(
        MovieItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view, clickFunc = onClick)
    }
}
