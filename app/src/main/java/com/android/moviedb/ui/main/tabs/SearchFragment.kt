package com.android.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.base.BaseFragment
import com.android.data.model.Movie
import com.android.moviedb.R
import com.android.moviedb.databinding.FragmentSearchBinding
import com.android.presentation.adapter.recyclerview.MovieSearchAdapter
import com.android.presentation.vm.SearchViewModel
import com.caner.common.Constants
import com.caner.common.Resource
import com.caner.common.ext.*
import com.caner.common.utils.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy {
        MovieSearchAdapter {
            movieClicked(it?.movieId)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.movieSearchRv.init(searchAdapter, listOf(VerticalSpaceItemDecoration(16.dp2px())))
        initObservers()

        binding.searchEt.afterTextChanged {
            viewModel.searchQuery.value = it
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.searchFlow.onEach { resource ->
                when (resource) {
                    is Resource.Loading -> setLoadingStatus(resource.status)
                    is Resource.Success -> setList(false, resource.data.movies)
                    is Resource.Empty -> setList(true, emptyList())
                    is Resource.Error -> toast(resource.apiError.message)
                }
            }.launchIn(this)
        }
    }

    private fun movieClicked(movieId: Int?) {
        val bundle = bundleOf(Constants.MOVIE_ID to (movieId ?: 0))
        findNavController().navigate(R.id.movieDetailFragment, bundle)
    }

    private fun setList(showEmptyView: Boolean, list: List<Movie>) {
        binding.emptyViewTv.visible(showEmptyView)
        searchAdapter.submitList(list)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}