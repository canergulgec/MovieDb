package com.android.moviedb.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.android.base.BaseFragment
import com.android.base.ext.*
import com.android.data.Constants
import com.android.presentation.utils.HorizontalSpaceItemDecoration
import com.android.presentation.utils.VerticalSpaceItemDecoration
import com.android.presentation.vm.MovieDetailViewModel
import com.android.moviedb.R
import com.android.presentation.adapter.recyclerview.MovieGenresAdapter
import com.android.presentation.adapter.recyclerview.MovieImagesAdapter
import com.android.presentation.adapter.recyclerview.MovieVideosAdapter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_movie_detail

    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieGenresAdapter = MovieGenresAdapter()
    private val movieImagesAdapter = MovieImagesAdapter()
    private val movieVideosAdapter = MovieVideosAdapter()
    private val adapter = ConcatAdapter(movieVideosAdapter, movieImagesAdapter)

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()
        setMovieGenres()

        movieGalleryRv.initPagerSnapHelper(adapter)
        val movieId = arguments?.getInt(Constants.MOVIE_ID)
        viewModel.getMovieDetail(movieId)
        viewModel.getMovieGallery(movieId)
    }

    private fun initObservers() {
        viewModel.getLoadingStatus()?.observeWith(viewLifecycleOwner) {
            setProgressStatus(it)
        }

        viewModel.getError()?.observeWith(viewLifecycleOwner) {
            setProgressStatus(false)
            toast("error happened ${it.code} ${it.message}")
        }

        viewModel.movieDetailLiveData.observeWith(viewLifecycleOwner) {
            movieTitleTv.text = it.title
            moviePosterIv.loadImage(it.poster?.original)
            movieGenresAdapter.submitList(it.genres)
        }

        viewModel.movieImageListLiveData.observeWith(viewLifecycleOwner) {
            movieImagesAdapter.submitList(it)
        }

        viewModel.movieVideoListLiveData.observeWith(viewLifecycleOwner) {
            movieVideosAdapter.submitList(it)
        }
    }

    private fun setMovieGenres() {
        val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        movieGenresRv.init(
            movieGenresAdapter, listOf(
                HorizontalSpaceItemDecoration(4.dp2px()),
                VerticalSpaceItemDecoration(4.dp2px())
            ),
            flexBoxLayoutManager
        )
    }
}