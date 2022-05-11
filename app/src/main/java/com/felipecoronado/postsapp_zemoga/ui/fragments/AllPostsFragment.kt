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
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.databinding.FragmentAllPostsBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.AllPostsAdapter
import com.felipecoronado.postsapp_zemoga.ui.viewmodels.PostsListSharedViewModel
import com.felipecoronado.postsapp_zemoga.ui.viewstates.AllPostsListViewState
import com.felipecoronado.postsapp_zemoga.ui.viewstates.FavoritePostsListViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPostsFragment : Fragment(), AllPostsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAllPostsBinding
    private val viewModel: PostsListSharedViewModel by activityViewModels()
    private var favoriteList = mutableListOf<FavoritePosts>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_all_posts,
                container,
                false
            )
        binding.lifecycleOwner = this
        viewModel.allViewState.observe(viewLifecycleOwner, ::handleViewState)
        viewModel.favoriteViewState.observe(viewLifecycleOwner, ::handleViewState)

        viewModel.getAllPosts()



        return binding.root
    }

    private fun handleViewState(viewState: AllPostsListViewState) {
        when (viewState) {
            is AllPostsListViewState.AllPostsNotFound -> Toast.makeText(
                context,
                "Could not load Posts",
                Toast.LENGTH_LONG
            ).show()
            else -> inflateRecycler(viewState as AllPostsListViewState.AllPostsList)

        }
    }

    private fun handleViewState(viewState: FavoritePostsListViewState) {
        val postList = viewState as FavoritePostsListViewState.FavoritePostList
        when (viewState) {
            is FavoritePostsListViewState.EmptyListAll -> favoriteList
            else -> favoriteList = postList.postsList as MutableList<FavoritePosts>
        }
    }

    private fun inflateRecycler(postsList: AllPostsListViewState.AllPostsList) {
        val adapter1 = AllPostsAdapter(postsList.posts as MutableList<PostsResponse>, this)

        if (!favoriteList.isNullOrEmpty()) {
            val sortedList = mutableListOf<PostsResponse>()
            val adapter2 = AllPostsAdapter(sortedList, this)
            binding.allPostsRecyclerView.adapter = adapter2
        }

        binding.allPostsRecyclerView.adapter = adapter1

    }

    override fun navigate(postId: Int, userId: Int) {
        val directionToFragment =
            PostsListFragmentDirections.actionPostsListFragmentToPostDescriptionFragment(postId)
        Navigation.findNavController(binding.root)
            .navigate(directionToFragment)
    }


}
