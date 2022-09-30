package com.caner.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.domain.model.Movie
import com.caner.core.base.BaseRecyclerAdapter
import com.caner.presentation.databinding.ItemMovieSearchBinding
import com.caner.presentation.ui.search.adapter.comparator.SearchItemDiffUtil
import com.caner.presentation.ui.search.adapter.viewholder.SearchViewHolder

class SearchAdapter(private val onClick: (Int) -> Unit) :
    BaseRecyclerAdapter<Movie, ItemMovieSearchBinding, SearchViewHolder>(
        SearchItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view, clickFunc = onClick)
    }
}
