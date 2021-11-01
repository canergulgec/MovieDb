package com.android.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.moviedb.R
import com.android.moviedb.databinding.FragmentHomeBinding
import com.android.moviedb.ui.movie.MovieFragment
import com.caner.presentation.adapter.viewpager.MoviePagerAdapter
import com.caner.data.Constants
import com.caner.core.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.moviePager.apply {
            adapter = MoviePagerAdapter(
                this@HomeFragment,
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

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
}
