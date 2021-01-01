package com.android.moviedb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.base.BaseFragment
import com.android.moviedb.databinding.FragmentSearchBinding
import com.android.presentation.vm.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}