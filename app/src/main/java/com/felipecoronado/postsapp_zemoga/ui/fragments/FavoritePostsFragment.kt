package com.felipecoronado.postsapp_zemoga.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.databinding.FragmentFavoritePostsBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.FavoritePostsAdapter
import com.felipecoronado.postsapp_zemoga.ui.viewmodels.PostsListSharedViewModel
import com.felipecoronado.postsapp_zemoga.ui.viewstates.FavoritePostsListViewState

class FavoritePostsFragment : Fragment(),FavoritePostsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentFavoritePostsBinding
    private val viewModel: PostsListSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_favorite_posts,
                container,
                false
            )

        binding.lifecycleOwner = this
        viewModel.favoriteViewState.observe(viewLifecycleOwner, ::handleViewState)

        viewModel.getAllFavoritesPosts()

        return binding.root
    }

    private fun handleViewState(viewState: FavoritePostsListViewState) {
        when (viewState) {
            is FavoritePostsListViewState.EmptyListAll -> Toast.makeText(
                context,
                "No posts marked as favorite",
                Toast.LENGTH_LONG
            ).show()
            else -> inflateRecycler(viewState as FavoritePostsListViewState.FavoritePostList)

        }
    }

    private fun inflateRecycler(postsList: FavoritePostsListViewState.FavoritePostList) {
        val adapter = FavoritePostsAdapter(postsList.postsList, this)
        binding.favoritePostRecycleView.adapter = adapter

    }

    override fun navigate(postId: Int, userId: Int) {
        val directionToFragment =
            PostsListFragmentDirections.actionPostsListFragmentToPostDescriptionFragment(postId)
        Navigation.findNavController(binding.root)
            .navigate(directionToFragment)
    }
}
