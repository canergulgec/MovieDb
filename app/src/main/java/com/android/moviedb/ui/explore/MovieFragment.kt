package com.android.moviedb.ui.explore

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.android.base.BaseFragment
import com.android.base.ext.*
import com.android.data.Constants
import com.android.data.model.Movie
import com.android.presentation.adapter.paging.MoviesPagingAdapter
import com.android.presentation.utils.VerticalSpaceItemDecoration
import com.android.presentation.vm.MovieViewModel
import com.android.moviedb.R
import com.android.presentation.worker.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    @Inject
    lateinit var navControllerProvider: Provider<NavController>

    private val navController: NavController
        get() = navControllerProvider.get()

    override val layoutId = R.layout.fragment_movies

    private val viewModel: MovieViewModel by viewModels()

    private var movieAdapter = MoviesPagingAdapter { movie: Movie? ->
        recyclerItemClicked(movie)
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
        initObservers()

        moviesRv.init(movieAdapter, listOf(VerticalSpaceItemDecoration(8.dp2px())))
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.moviePagingFlow.collectLatest { pagingData ->
                movieAdapter.submitData(lifecycle, pagingData)
                delay(2000)
                stopShimmer()
                moviesRv.show()
            }
        }
    }

    private fun stopShimmer() {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.hide()
    }

    private fun recyclerItemClicked(movie: Movie?) {
        val bundle = bundleOf(Constants.MOVIE_ID to (movie?.movieId ?: 0))
        navController.navigate(R.id.movieDetailFragment, bundle)
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

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }
}