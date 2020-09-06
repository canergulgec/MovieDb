package com.android.moviedb.ui.explore

import android.os.Bundle
import com.android.moviedb.R
import com.android.base.BaseFragment
import com.android.data.Constants
import com.android.presentation.adapter.viewpager.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_explore.*

@AndroidEntryPoint
class ExploreFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_explore

    override fun initView(savedInstanceState: Bundle?) {

        moviePager.apply {
            adapter = MoviePagerAdapter(
                this@ExploreFragment,
                MovieFragment.newInstance(Constants.NOW_PLAYING_MOVIES),
                MovieFragment.newInstance(Constants.UPCOMING_MOVIES)
            )
            TabLayoutMediator(movieTabLayout, this) { tab, position ->
                val tabTitleArray =
                    resources.getStringArray(R.array.movie_tab_titles)
                tab.text = tabTitleArray[position]
            }.attach()
        }
    }
}