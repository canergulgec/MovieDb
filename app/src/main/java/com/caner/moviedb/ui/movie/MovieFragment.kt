package com.caner.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.caner.data.model.Movie
import com.caner.presentation.adapter.paging.MovieLoadStateAdapter
import com.caner.presentation.adapter.paging.MoviesPagingAdapter
import com.caner.presentation.viewmodel.MovieViewModel
import com.caner.presentation.worker.NotificationWorker
import com.caner.data.Constants
import com.caner.core.base.BaseFragment
import com.caner.core.extension.withLoadStateAll
import com.caner.moviedb.R
import com.caner.moviedb.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter by lazy {
        MoviesPagingAdapter { movie: Movie? ->
            movieClicked(movie)
        }
    }

    companion object {
        fun newInstance(movieType: Int): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.MOVIE_TYPE, movieType)
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (movieAdapter.itemCount == 0) {
            initPagingFlow()
        }

        binding.moviesRv.apply {
            adapter = movieAdapter.withLoadStateAll(
                refresh = MovieLoadStateAdapter(movieAdapter::refresh),
                header = MovieLoadStateAdapter(movieAdapter::retry),
                footer = MovieLoadStateAdapter(movieAdapter::retry)
            )
        }
    }

    private fun initPagingFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviePagingFlow.collectLatest { pagingData ->
                    movieAdapter.submitData(lifecycle, pagingData)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieAdapter.loadStateFlow
                    // Only emit when REFRESH LoadState for RemoteMediator changes.
                    .distinctUntilChangedBy { it.refresh }
                    .collect {
                        if (it.refresh is LoadState.Loading) {
                            (binding.moviesRv.layoutManager as GridLayoutManager).spanCount = 1
                        } else {
                            (binding.moviesRv.layoutManager as GridLayoutManager).spanCount = 2
                        }
                    }
            }
        }
    }

    private fun movieClicked(movie: Movie?) {
        val bundle = bundleOf(Constants.MOVIE_ID to (movie?.movieId ?: 0))
        findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
        startWorker(movie?.title, movie?.overview)
    }

    private fun startWorker(title: String?, overview: String?) {
        val inputs = Data.Builder().putString(Constants.MOVIE_TITLE, title)
            .putString(Constants.MOVIE_OVERVIEW, overview).build()
        val request = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(inputs)
            .build()
        context?.let { WorkManager.getInstance(it).enqueue(request) }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate
}
