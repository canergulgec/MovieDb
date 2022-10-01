package com.caner.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.caner.domain.model.Movie
import com.caner.presentation.databinding.ItemMovieSearchBinding
import com.caner.presentation.ui.search.adapter.comparator.SearchItemComparator
import com.caner.presentation.ui.search.adapter.viewholder.SearchViewHolder

class SearchAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Movie, SearchViewHolder>(SearchItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onClick.invoke(movie.movieId)
            }
        }
    }
}
