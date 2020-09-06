package com.android.moviedb.ui.explore

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.android.base.BaseFragment
import com.android.base.NetworkState
import com.android.base.ext.*
import com.android.data.Constants
import com.android.data.model.Movie
import com.android.presentation.adapter.paging.MoviesPagingAdapter
import com.android.presentation.utils.VerticalSpaceItemDecoration
import com.android.presentation.vm.MovieViewModel
import com.android.moviedb.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
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
        initObservers()

        moviesRv.init(movieAdapter, listOf(VerticalSpaceItemDecoration(8.dp2px())))
        viewModel.getMovies(movieType)
    }

    private fun initObservers() {
        viewModel.getError()?.observeWith(viewLifecycleOwner) {
            setProgressStatus(NetworkState.Failed)
            stopShimmer()
            toast("error happened ${it.code} ${it.message}")
        }

        viewModel.moviePagingLiveData.observeWith(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
            Looper.myLooper()?.let { looper ->
                Handler(looper).postDelayed({
                    stopShimmer()
                    moviesRv.show()
                }, 2000)
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