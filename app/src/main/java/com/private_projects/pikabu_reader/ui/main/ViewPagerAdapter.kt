package com.private_projects.pikabu_reader.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.private_projects.pikabu_reader.ui.best.BestFragment
import com.private_projects.pikabu_reader.ui.fresh.FreshFragment
import com.private_projects.pikabu_reader.ui.hot.HotFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = arrayOf(
        HotFragment.newInstance(),
        BestFragment.newInstance(),
        FreshFragment.newInstance()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> fragments[BEST_FRAGMENT]
            2 -> fragments[FRESH_FRAGMENT]
            else -> fragments[HOT_FRAGMENT]
        }
    }
}