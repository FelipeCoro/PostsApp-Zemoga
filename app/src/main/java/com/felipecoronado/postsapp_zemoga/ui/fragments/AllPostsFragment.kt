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
import com.felipecoronado.postsapp_zemoga.databinding.FragmentAllPostsBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.AllPostsAdapter
import com.felipecoronado.postsapp_zemoga.ui.viewmodels.PostsListSharedViewModel
import com.felipecoronado.postsapp_zemoga.ui.viewstates.PostsListViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPostsFragment : Fragment(), AllPostsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAllPostsBinding
    private val viewModel: PostsListSharedViewModel by activityViewModels()

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
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)

        viewModel.getAllPosts()

        return binding.root
    }

    private fun handleViewState(viewState: PostsListViewState) {
        when (viewState) {
            is PostsListViewState.PostsNotFound -> Toast.makeText(
                context,
                "Could not load Posts",
                Toast.LENGTH_LONG
            ).show()
            else -> inflateRecycler(viewState as PostsListViewState.AllPostsList)

        }
    }

    private fun inflateRecycler(postsList: PostsListViewState.AllPostsList) {
        val adapter = AllPostsAdapter(postsList.posts, this)
        binding.allPostsRecyclerView.adapter = adapter

    }

    override fun navigate(postId: Int, userId: Int) {
        val directionToFragment =
            PostsListFragmentDirections.actionPostsListFragmentToPostDescriptionFragment(postId)
        Navigation.findNavController(binding.root)
            .navigate(directionToFragment)
    }


}
