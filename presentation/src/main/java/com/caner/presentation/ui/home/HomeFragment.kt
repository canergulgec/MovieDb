package com.caner.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.core.base.BaseFragment
import com.caner.core.constants.HttpParams
import com.caner.presentation.R
import com.caner.presentation.databinding.FragmentHomeBinding
import com.caner.presentation.ui.home.adapter.viewpager.MoviePagerAdapter
import com.caner.presentation.ui.home.fragment.MovieFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.moviePager.apply {
            adapter = MoviePagerAdapter(
                this@HomeFragment,
                MovieFragment.newInstance(HttpParams.NOW_PLAYING_MOVIES),
                MovieFragment.newInstance(HttpParams.UPCOMING_MOVIES)
            )
            TabLayoutMediator(binding.movieTabLayout, this) { tab, position ->
                val tabTitleArray =
                    resources.getStringArray(R.array.movie_tab_titles)
                tab.text = tabTitleArray[position]
            }.attach()
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
}
