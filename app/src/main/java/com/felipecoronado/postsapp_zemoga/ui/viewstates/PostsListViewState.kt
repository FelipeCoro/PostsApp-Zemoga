package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

sealed interface PostsListViewState {

    data class AllPostsList(val allPosts: List<PostsResponse>) :
        PostsListViewState
    data class FavoritePostsList(val favoritePosts: List<FavoritePosts>) :
        PostsListViewState

    object PostsNotFound :
       PostsListViewState

}
