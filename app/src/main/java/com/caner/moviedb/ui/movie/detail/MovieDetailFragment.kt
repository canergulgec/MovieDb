package com.caner.moviedb.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.caner.presentation.adapter.recyclerview.MovieGenresAdapter
import com.caner.presentation.viewmodel.MovieDetailViewModel
import com.caner.core.base.BaseFragment
import com.caner.presentation.adapter.decoration.HorizontalSpaceItemDecoration
import com.caner.presentation.adapter.decoration.VerticalSpaceItemDecoration
import com.caner.core.extension.init
import com.caner.core.extension.px
import com.caner.core.extension.toast
import com.caner.moviedb.databinding.FragmentMovieDetailBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    private val viewModel: MovieDetailViewModel by viewModels()
    private val movieDetailArgs: MovieDetailFragmentArgs by navArgs()

    private val movieGenresAdapter by lazy {
        MovieGenresAdapter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()
        setMovieGenres()

        viewModel.getMovieDetail(movieDetailArgs.movieId)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        viewModel.uiState.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle).onEach { state ->
            showLoading(state.isFetchingMovieDetail)
            state.movieDetailModel?.let {
                binding.item = it
                movieGenresAdapter.submitList(it.genres)
            }

            state.errorMessage.firstOrNull()?.let {
                toast("error happened: ${it.message}")
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setMovieGenres() {
        val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        binding.movieGenresRv.init(
            adapter = movieGenresAdapter,
            itemDecoration = listOf(
                HorizontalSpaceItemDecoration(4.px),
                VerticalSpaceItemDecoration(4.px)
            ),
            rvLayoutManager = flexBoxLayoutManager
        )
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = FragmentMovieDetailBinding::inflate
}
