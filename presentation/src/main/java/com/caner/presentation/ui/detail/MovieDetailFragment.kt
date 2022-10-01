package com.caner.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.caner.presentation.utils.decoration.HorizontalSpaceItemDecoration
import com.caner.presentation.utils.decoration.VerticalSpaceItemDecoration
import com.caner.presentation.utils.extension.init
import com.caner.presentation.utils.extension.px
import com.caner.presentation.utils.extension.toast
import com.caner.presentation.utils.widget.FullScreenProgressDialog
import com.caner.presentation.R
import com.caner.presentation.databinding.FragmentMovieDetailBinding
import com.caner.presentation.ui.detail.adapter.MovieGenresAdapter
import com.caner.presentation.ui.detail.viewmodel.MovieDetailViewModel
import com.caner.presentation.utils.delegate.viewBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private val binding by viewBinding(FragmentMovieDetailBinding::bind)
    private val viewModel: MovieDetailViewModel by viewModels()
    private val movieDetailArgs: MovieDetailFragmentArgs by navArgs()

    private val progressDialog by lazy { FullScreenProgressDialog(requireContext()) }

    private val movieGenresAdapter by lazy {
        MovieGenresAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setMovieGenres()

        viewModel.getMovieDetail(movieDetailArgs.movieId)
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        viewModel.uiState.flowWithLifecycle(lifecycle = viewLifecycleOwner.lifecycle).onEach { state ->
            showLoadingDialog(state.isFetchingMovieDetail)
            state.movieDetailModel?.takeIf { !state.isFetchingMovieDetail }?.let {
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
            itemDecoration = listOf(HorizontalSpaceItemDecoration(4.px), VerticalSpaceItemDecoration(4.px)),
            rvLayoutManager = flexBoxLayoutManager
        )
    }

    private fun showLoadingDialog(isFetching: Boolean) {
        if (isFetching && !progressDialog.isShowing) {
            progressDialog.show()
        } else {
            progressDialog.hide()
        }
    }
}
