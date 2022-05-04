package com.felipecoronado.postsapp_zemoga.ui.fragments.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.felipecoronado.postsapp_zemoga.ui.fragments.AllPostsFragment
import com.felipecoronado.postsapp_zemoga.ui.fragments.FavoritePostsFragment

class ViewPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllPostsFragment()
            1 -> FavoritePostsFragment()
            else ->{throw Resources.NotFoundException("Position not found")}
        }
    }
}

