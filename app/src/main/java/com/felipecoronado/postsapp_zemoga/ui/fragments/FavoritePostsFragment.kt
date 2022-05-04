package com.felipecoronado.postsapp_zemoga.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.databinding.FragmentFavoritePostsBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.AllPostsAdapter

class FavoritePostsFragment : Fragment() {
    private lateinit var binding: FragmentFavoritePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite_posts, container, false)

        inflateRecycler()

        return binding.root
    }

    private fun inflateRecycler(){
        val adapter = AllPostsAdapter()
        binding.favoritePostsRecyclerView.adapter = adapter
    }
}
