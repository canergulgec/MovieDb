package com.caner.presentation.ui.home.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.caner.domain.model.Movie
import com.caner.presentation.databinding.ItemMovieBinding
import com.caner.presentation.ui.home.adapter.comparator.MovieItemComparator
import com.caner.presentation.ui.home.adapter.viewholder.MovieViewHolder

class MoviesPagingAdapter(private val onClick: (Int) -> Unit) : PagingDataAdapter<Movie, MovieViewHolder>(MovieItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onClick.invoke(movie.movieId)
            }
        }
    }
}
