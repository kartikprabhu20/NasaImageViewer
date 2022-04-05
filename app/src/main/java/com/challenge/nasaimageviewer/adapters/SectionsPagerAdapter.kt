package com.challenge.nasaimageviewer.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.challenge.nasaimageviewer.ui.main.FullScreenFragment
import com.challenge.nasaimageviewer.ui.main.GalleryFragment


const val FULL_SCREEN_PAGE_INDEX = 0
const val GALLERY_PAGE_INDEX = 1

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FULL_SCREEN_PAGE_INDEX to { FullScreenFragment() },
        GALLERY_PAGE_INDEX to { GalleryFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}