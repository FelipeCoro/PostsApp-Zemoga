package com.felipecoronado.postsapp_zemoga.ui.viewstates

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse

sealed interface PostDescriptionViewState {

    data class PostFetchSuccessful(val post: PostsResponse) : PostDescriptionViewState
    data class UserFetchSuccessful(val user: UsersResponse) : PostDescriptionViewState
    data class PostCommentsList(val commentsList: List<CommentsResponse>) : PostDescriptionViewState
    data class FavoritePostsList(val posts: List<FavoritePosts>) :  PostDescriptionViewState

    object NullPost : PostDescriptionViewState
    object PostsNotFound : PostDescriptionViewState
    object UserNotFound : PostDescriptionViewState
    object CommentsNotFound : PostDescriptionViewState



}
