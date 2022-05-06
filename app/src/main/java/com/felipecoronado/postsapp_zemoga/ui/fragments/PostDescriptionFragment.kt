package com.felipecoronado.postsapp_zemoga.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import com.felipecoronado.postsapp_zemoga.databinding.FragmentPostDescriptionBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.PostCommentsAdapter
import com.felipecoronado.postsapp_zemoga.ui.viewmodels.PostDescriptionViewModel
import com.felipecoronado.postsapp_zemoga.ui.viewstates.PostDescriptionViewState

class PostDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentPostDescriptionBinding
    private val viewModel: PostDescriptionViewModel by activityViewModels()
    private val args: PostDescriptionFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_post_description,
                container,
                false
            )


        binding.lifecycleOwner = this
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)

        val postId = args.postId

        viewModel.getPost(postId)

        return binding.root
    }



    private fun handleViewState(viewState: PostDescriptionViewState) {
        when (viewState) {
            is PostDescriptionViewState.PostFetchSuccessful -> populatePostBody(viewState.post)
            is PostDescriptionViewState.UserFetchSuccessful -> populateUser(viewState.user)
            is PostDescriptionViewState.PostCommentsList -> populateCommentsRecyclerView(viewState.commentsList)
            is PostDescriptionViewState.UserNotFound -> showError("ERROR1")
            is PostDescriptionViewState.PostsNotFound -> showError("ERROR2")
            else -> showError("ERROR3")

        }
    }

    private fun populatePostBody(post: PostsResponse) {
        viewModel.getUser(post.userId)
        viewModel.getComments(post.id)
        binding.descriptionContentTextView.text =post.body
    }

    private fun populateUser(user: UsersResponse) {
        binding.run {
            userNameTextView.text = user.name
            userEmailTextView.text = user.email
            userPhoneTextView.text = user.phone
            userWebsiteTextView.text = user.website
        }

    }
    private fun populateCommentsRecyclerView(commentsList: List<CommentsResponse>){
        val adapter = PostCommentsAdapter(commentsList)
        binding.commentsRecyclerView .adapter = adapter
    }

    private fun showError(errorType: String) {

        when (errorType) {
            "ERROR1" -> Toast.makeText(activity, "tooLazy", Toast.LENGTH_LONG).show()
            "ERROR2" -> Toast.makeText(activity, "tooLazy", Toast.LENGTH_LONG).show()
            "ERROR3" -> Toast.makeText(activity, "tooLazy", Toast.LENGTH_LONG).show()
        }
    }
}
