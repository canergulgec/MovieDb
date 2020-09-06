package com.android.moviedb.ui.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.android.moviedb.R
import com.android.base.BaseFragment
import com.android.presentation.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_search

    private val viewModel: SearchViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
    }
}