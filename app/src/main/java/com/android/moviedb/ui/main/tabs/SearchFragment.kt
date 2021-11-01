package com.android.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.data.model.Movie
import com.android.moviedb.R
import com.android.moviedb.databinding.FragmentSearchBinding
import com.caner.presentation.adapter.recyclerview.MovieSearchAdapter
import com.caner.presentation.vm.SearchViewModel
import com.android.data.Constants
import com.android.domain.viewstate.Resource
import com.caner.core.base.BaseFragment
import com.caner.core.decoration.VerticalSpaceItemDecoration
import com.caner.core.extension.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

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
        binding.movieSearchRv.init(
            searchAdapter,
            listOf(
                VerticalSpaceItemDecoration(16.dp2px()),
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            )
        )
        binding.searchEt.afterTextChanged {
            viewModel.searchQuery.value = it
        }

        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchFlow.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> showLoading(resource.status)
                        is Resource.Success -> setList(false, resource.data.movies)
                        is Resource.Empty -> setList(true, emptyList())
                        is Resource.Error -> toast(resource.apiError.message)
                        else -> Timber.v("Initial state")
                    }
                }
            }
        }
    }

    private fun movieClicked(movieId: Int?) {
        val bundle = bundleOf(Constants.MOVIE_ID to (movieId ?: 0))
        findNavController().navigate(R.id.action_searchFragment_to_movieDetailFragment, bundle)
    }

    private fun setList(showEmptyView: Boolean, list: List<Movie>) {
        binding.emptyViewTv.visible(showEmptyView)
        searchAdapter.submitList(list.sortedByDescending { it.popularity })
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}
