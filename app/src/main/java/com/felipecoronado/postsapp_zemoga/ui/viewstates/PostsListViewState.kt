package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse
import retrofit2.Response

sealed interface PostsListViewState{

data class AllPostsList(val posts: List<PostsResponse>) : PostsListViewState

object  PostsNotFound: PostsListViewState
}
