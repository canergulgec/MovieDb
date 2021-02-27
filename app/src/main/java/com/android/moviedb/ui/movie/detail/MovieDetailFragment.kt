package com.android.moviedb.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.android.base.BaseFragment
import com.caner.common.ext.*
import com.caner.common.Constants
import com.caner.common.utils.HorizontalSpaceItemDecoration
import com.caner.common.utils.VerticalSpaceItemDecoration
import com.android.presentation.vm.MovieDetailViewModel
import com.android.moviedb.databinding.FragmentMovieDetailBinding
import com.android.presentation.adapter.recyclerview.MovieGenresAdapter
import com.android.presentation.adapter.recyclerview.MovieImagesAdapter
import com.android.presentation.adapter.recyclerview.MovieVideosAdapter
import com.caner.common.Resource
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieGenresAdapter = MovieGenresAdapter()
    private val movieImagesAdapter = MovieImagesAdapter()
    private val movieVideosAdapter = MovieVideosAdapter()
    private val adapter = ConcatAdapter(movieImagesAdapter, movieVideosAdapter)

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()
        setMovieGenres()

        binding.movieGalleryRv.initPagerSnapHelper(adapter)
        val movieId = arguments?.getInt(Constants.MOVIE_ID)
        viewModel.getMovieDetail(movieId)
        viewModel.getMovieGallery(movieId)
    }

    private fun initObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.movieDetailState.onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data.apply {
                            binding.item = this
                            movieGenresAdapter.submitList(genres)
                        }
                    }
                    else -> Timber.v("Initial Empty state")
                }
            }.launchIn(this)

            viewModel.movieBackdropState.onEach { resource ->
                when (resource) {
                    is Resource.Success -> movieImagesAdapter.submitList(resource.data)
                    else -> Timber.v("Initial Empty state")
                }
            }.launchIn(this)

            viewModel.movieVideoState.onEach { resource ->
                when (resource) {
                    is Resource.Success -> movieVideosAdapter.submitList(resource.data)
                    else -> Timber.v("Initial Empty state")
                }
            }.launchIn(this)

            viewModel.getLoadingStatus().onEach {
                setLoadingStatus(it)
            }.launchIn(this)

            viewModel.getError().onEach {
                if (it.code != -1) {
                    toast("error happened ${it.code} ${it.message}")
                }
            }.launchIn(this)
        }
    }

    private fun setMovieGenres() {
        val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        binding.movieGenresRv.init(
            movieGenresAdapter,
            listOf(
                HorizontalSpaceItemDecoration(4.dp2px()),
                VerticalSpaceItemDecoration(4.dp2px())
            ),
            flexBoxLayoutManager
        )
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = FragmentMovieDetailBinding::inflate
}