package com.android.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.android.base.BaseFragment
import com.android.data.model.Movie
import com.android.moviedb.R
import com.android.moviedb.databinding.FragmentMoviesBinding
import com.android.presentation.adapter.paging.MovieLoadStateAdapter
import com.android.presentation.adapter.paging.MoviesPagingAdapter
import com.android.presentation.vm.MovieViewModel
import com.android.presentation.worker.NotificationWorker
import com.caner.common.Constants
import com.caner.common.ext.dp2px
import com.caner.common.ext.withLoadStateAll
import com.caner.common.utils.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter by lazy {
        MoviesPagingAdapter { movie: Movie? ->
            movieClicked(movie)
        }
    }

    companion object {
        const val MOVIE_TYPE = "MOVIE_TYPE"

        fun newInstance(movieType: Int): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_TYPE, movieType)
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        val movieType = arguments?.getInt(MOVIE_TYPE, 1) ?: 1
        viewModel.setMovieType(movieType)

        if (movieAdapter.itemCount == 0) {
            initPagingFlow()
        }

        /**
         * Span count should be 1 when loader state is visible //TODO
         */
        binding.moviesRv.apply {
            addItemDecoration(VerticalSpaceItemDecoration(8.dp2px()))
            adapter = movieAdapter.withLoadStateAll(
                refresh = MovieLoadStateAdapter(movieAdapter::refresh),
                header = MovieLoadStateAdapter(movieAdapter::retry),
                footer = MovieLoadStateAdapter(movieAdapter::retry)
            )
        }
    }

    private fun initPagingFlow() {
        lifecycleScope.launchWhenResumed {
            viewModel.moviePagingFlow.collectLatest { pagingData ->
                movieAdapter.submitData(lifecycle, pagingData)
            }
        }
    }

    private fun movieClicked(movie: Movie?) {
        val bundle = bundleOf(Constants.MOVIE_ID to (movie?.movieId ?: 0))
        findNavController().navigate(R.id.navigate_to_movieDetailFragment, bundle)
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
