package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

sealed interface PostsListViewState{

data class AllPostsList(val posts: List<PostsResponse>) : PostsListViewState

object  PostsNotFound: PostsListViewState
}
