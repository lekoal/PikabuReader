package com.private_projects.pikabu_reader.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.private_projects.pikabu_reader.R
import com.private_projects.pikabu_reader.databinding.ActivityMainBinding

const val HOT_FRAGMENT = 0
const val BEST_FRAGMENT = 1
const val FRESH_FRAGMENT = 2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setViewPager()

    }

    private fun setViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { _, _ -> }.attach()
        activeTabListener()
    }

    private fun activeTabListener() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setHighLightedTab(position)
                super.onPageSelected(position)
            }
        })
        binding.tabLayout.getTabAt(FRESH_FRAGMENT)?.select()
        binding.tabLayout.getTabAt(HOT_FRAGMENT)?.select()
    }

    private fun setHighLightedTab(position: Int) {
        when (position) {
            HOT_FRAGMENT -> {
                activateTab(HOT_FRAGMENT)
                deactivateTab(BEST_FRAGMENT)
                deactivateTab(FRESH_FRAGMENT)
            }
            BEST_FRAGMENT -> {
                activateTab(BEST_FRAGMENT)
                deactivateTab(HOT_FRAGMENT)
                deactivateTab(FRESH_FRAGMENT)
            }
            FRESH_FRAGMENT -> {
                activateTab(FRESH_FRAGMENT)
                deactivateTab(BEST_FRAGMENT)
                deactivateTab(HOT_FRAGMENT)
            }
        }
    }

    private fun activateTab(tabIndex: Int) {
        binding.tabLayout.getTabAt(tabIndex)?.customView = null
        when (tabIndex) {
            HOT_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_hot_selected)
            BEST_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_best_selected)
            FRESH_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_fresh_selected)
        }
    }

    private fun deactivateTab(tabIndex: Int) {
        binding.tabLayout.getTabAt(tabIndex)?.customView = null
        when (tabIndex) {
            HOT_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_hot_unselected)
            BEST_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_best_unselected)
            FRESH_FRAGMENT -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_fresh_unselected)
        }
    }
}