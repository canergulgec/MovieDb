package com.caner.presentation.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.caner.core.constants.Constants
import com.caner.core.base.BaseFragment
import com.caner.core.extension.px
import com.caner.core.extension.withLoadStateAll
import com.caner.presentation.utils.decoration.HorizontalSpaceItemDecoration
import com.caner.presentation.databinding.FragmentMoviesBinding
import com.caner.presentation.ui.home.HomeFragmentDirections
import com.caner.presentation.ui.home.adapter.loadstate.MovieLoadStateAdapter
import com.caner.presentation.ui.home.adapter.paging.MoviesPagingAdapter
import com.caner.presentation.ui.home.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMoviesBinding>() {
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

    override fun initView(savedInstanceState: Bundle?) {
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
        viewModel.movieUiState.flowWithLifecycle(
            minActiveState = Lifecycle.State.CREATED,
            lifecycle = viewLifecycleOwner.lifecycle
        ).onEach { state ->
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

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate
}
