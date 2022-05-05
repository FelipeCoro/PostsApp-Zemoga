package com.felipecoronado.postsapp_zemoga.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.databinding.FragmentPostsListBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PostsListFragment: Fragment(){
    private lateinit var binding: FragmentPostsListBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_posts_list, container, false)

        tabLayout = binding.postsListTabLayout
        viewPager = binding.postsListViewPager2
        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> { "ALL" }
                1 -> { "FAVORITES" }
                else -> { throw Resources.NotFoundException("Position not found") }
            }
        }.attach()

        return binding.root
    }
}
