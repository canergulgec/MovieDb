package com.caner.moviedb.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.caner.moviedb.ui.movie.MovieFragment
import com.caner.presentation.adapter.viewpager.MoviePagerAdapter
import com.caner.core.base.BaseFragment
import com.caner.core.constants.HttpParams
import com.caner.moviedb.R
import com.caner.moviedb.databinding.FragmentHomeBinding
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
