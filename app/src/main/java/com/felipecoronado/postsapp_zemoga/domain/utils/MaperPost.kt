package com.felipecoronado.postsapp_zemoga.domain.utils

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

class MaperPost {

    companion object {
        fun fromFavoriteToPostResponse(
            favoritePost: FavoritePosts
        ): PostsResponse {
            return PostsResponse(
                favoritePost.id,
                favoritePost.userId,
                favoritePost.title,
                favoritePost.body,
                true
            )
        }
    }

}
