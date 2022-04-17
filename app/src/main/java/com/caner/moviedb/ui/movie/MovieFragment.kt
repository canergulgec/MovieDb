package com.caner.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.caner.core.Constants
import com.caner.core.base.BaseFragment
import com.caner.core.extension.px
import com.caner.core.extension.withLoadStateAll
import com.caner.moviedb.databinding.FragmentMoviesBinding
import com.caner.moviedb.ui.main.tabs.HomeFragmentDirections
import com.caner.presentation.adapter.decoration.HorizontalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMoviesBinding>() {
    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter by lazy {
        MoviesPagingAdapter(onClick = { movie ->
            openMovieDetail(movie)
            startWorker(movie?.title, movie?.overview)
        })
    }

    companion object {
        fun newInstance(moviePath: String): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.MOVIE_PATH, moviePath)
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (movieAdapter.itemCount == 0) {
            initPagingFlow()
        }

        binding.moviesRv.apply {
            setHasFixedSize(true)
            addItemDecoration(HorizontalSpaceItemDecoration(12.px))
            adapter = movieAdapter.withLoadStateAll(
                refresh = MovieLoadStateAdapter(movieAdapter::refresh),
                header = MovieLoadStateAdapter(movieAdapter::retry),
                footer = MovieLoadStateAdapter(movieAdapter::retry)
            )
        }
    }

    private fun initPagingFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieUiState.collect { uiState ->
                    uiState.moviesPagingFlow?.collectLatest {
                        movieAdapter.submitData(it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieAdapter.loadStateFlow
                    // Only emit when REFRESH LoadState for RemoteMediator changes.
                    .distinctUntilChangedBy { it.refresh }
                    .collect {
                        (binding.moviesRv.layoutManager as GridLayoutManager).spanCount =
                            if (it.refresh is LoadState.Loading) Constants.SPAN_COUNT_1 else Constants.SPAN_COUNT_2
                    }
            }
        }
    }

    private fun openMovieDetail(movie: Movie?) {
        val detailAction = HomeFragmentDirections.movieDetailAction(movie?.movieId ?: 0)
        findNavController().navigate(detailAction)
    }

    private fun startWorker(title: String?, overview: String?) {
        val inputs = Data.Builder().putString(Constants.MOVIE_TITLE, title)
            .putString(Constants.MOVIE_OVERVIEW, overview).build()
        val request = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(inputs)
            .build()
        WorkManager.getInstance(requireContext()).enqueue(request)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate
}
