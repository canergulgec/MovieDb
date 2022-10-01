package com.caner.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.caner.domain.model.remote.MovieGenre
import com.caner.presentation.databinding.ItemGenreBinding
import com.caner.presentation.ui.detail.adapter.comparator.MovieGenreItemComparator
import com.caner.presentation.ui.detail.adapter.viewholder.MovieGenresViewHolder

class MovieGenresAdapter :
    ListAdapter<MovieGenre, MovieGenresViewHolder>(MovieGenreItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        val view =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }
}
