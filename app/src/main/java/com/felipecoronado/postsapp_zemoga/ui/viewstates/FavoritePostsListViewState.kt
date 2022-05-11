package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts

sealed interface FavoritePostsListViewState {

    data class FavoritePostList(val postsList: List<FavoritePosts>) :
        FavoritePostsListViewState
    object EmptyListAll :
        FavoritePostsListViewState


}
