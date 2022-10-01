package com.caner.presentation.ui.home.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.domain.model.Movie
import com.caner.core.BasePagingAdapter
import com.caner.presentation.databinding.ItemMovieBinding
import com.caner.presentation.ui.home.adapter.comparator.MovieItemDiffUtil
import com.caner.presentation.ui.home.adapter.viewholder.MovieViewHolder

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
