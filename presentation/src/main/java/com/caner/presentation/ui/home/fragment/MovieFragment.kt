package com.caner.presentation.ui.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.caner.presentation.utils.Constants
import com.caner.presentation.utils.extension.px
import com.caner.presentation.utils.extension.withLoadStateAll
import com.caner.presentation.R
import com.caner.presentation.utils.decoration.HorizontalSpaceItemDecoration
import com.caner.presentation.databinding.FragmentMoviesBinding
import com.caner.presentation.ui.home.HomeFragmentDirections
import com.caner.presentation.ui.home.adapter.loadstate.MovieLoadStateAdapter
import com.caner.presentation.ui.home.adapter.paging.MoviesPagingAdapter
import com.caner.presentation.ui.home.viewmodel.MovieViewModel
import com.caner.presentation.utils.delegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movies) {
    private val binding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter by lazy {
        MoviesPagingAdapter(onClick = { movieID ->
            viewModel.navigateToMovieDetail(HomeFragmentDirections.movieDetailAction(movieID))
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPagingFlow()
        binding.moviesRv.run {
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
        viewModel.movieUiState.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle)
            .onEach { state ->
                state.moviesPagingFlow?.collectLatest {
                    movieAdapter.submitData(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)


        movieAdapter.loadStateFlow.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle)
            .onEach {
                (binding.moviesRv.layoutManager as GridLayoutManager).spanCount =
                    when (it.refresh) {
                        is LoadState.Loading -> Constants.SPAN_COUNT_1
                        else -> Constants.SPAN_COUNT_2
                    }
            }
            .distinctUntilChangedBy { it.refresh }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
