package com.caner.presentation.ui.home.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IndexOutOfBoundsException

class MoviePagerAdapter(
    fragment: Fragment,
    private val nowPlayingMoviesFragment: Fragment,
    private val upcomingMoviesFragment: Fragment
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> nowPlayingMoviesFragment
            1 -> upcomingMoviesFragment
            else -> throw IndexOutOfBoundsException()
        }
    }
}
