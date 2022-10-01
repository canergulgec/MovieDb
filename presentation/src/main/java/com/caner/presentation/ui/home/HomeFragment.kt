package com.caner.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.caner.presentation.utils.Constants
import com.caner.presentation.R
import com.caner.presentation.databinding.FragmentHomeBinding
import com.caner.presentation.ui.home.adapter.viewpager.MoviePagerAdapter
import com.caner.presentation.ui.home.fragment.MovieFragment
import com.caner.presentation.utils.delegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

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
}
