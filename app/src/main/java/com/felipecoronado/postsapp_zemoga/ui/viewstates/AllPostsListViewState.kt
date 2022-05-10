package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

sealed interface AllPostsListViewState {

    data class AllPostsList(val posts: List<PostsResponse>) :
        AllPostsListViewState
    object AllPostsNotFound :
       AllPostsListViewState

}
