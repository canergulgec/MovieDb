package com.caner.moviedb.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.caner.presentation.adapter.recyclerview.MovieGenresAdapter
import com.caner.presentation.viewmodel.MovieDetailViewModel
import com.caner.core.base.BaseFragment
import com.caner.core.decoration.HorizontalSpaceItemDecoration
import com.caner.core.decoration.VerticalSpaceItemDecoration
import com.caner.core.extension.dp2px
import com.caner.core.extension.init
import com.caner.core.extension.toast
import com.caner.moviedb.databinding.FragmentMovieDetailBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    // Loading state
                    showLoading(uiState.isFetchingMovieDetail)

                    uiState.movieDetailModel?.let {
                        binding.item = it
                        movieGenresAdapter.submitList(it.genres)
                    }

                    uiState.userMessages.firstOrNull()?.let { userMessage ->
                        toast("error happened: ${userMessage.message}")
                        viewModel.userMessageShown(userMessage.id)
                    }
                }
            }
        }
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
                HorizontalSpaceItemDecoration(4.dp2px()),
                VerticalSpaceItemDecoration(4.dp2px())
            ),
            rvLayoutManager = flexBoxLayoutManager
        )
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = FragmentMovieDetailBinding::inflate
}
