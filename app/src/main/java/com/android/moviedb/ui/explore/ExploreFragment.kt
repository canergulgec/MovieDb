package com.android.moviedb.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.moviedb.R
import com.android.base.BaseFragment
import com.android.data.Constants
import com.android.moviedb.databinding.FragmentExploreBinding
import com.android.presentation.adapter.viewpager.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.moviePager.apply {
            adapter = MoviePagerAdapter(
                this@ExploreFragment,
                MovieFragment.newInstance(Constants.NOW_PLAYING_MOVIES),
                MovieFragment.newInstance(Constants.UPCOMING_MOVIES)
            )
            TabLayoutMediator(binding.movieTabLayout, this) { tab, position ->
                val tabTitleArray =
                    resources.getStringArray(R.array.movie_tab_titles)
                tab.text = tabTitleArray[position]
            }.attach()
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
}