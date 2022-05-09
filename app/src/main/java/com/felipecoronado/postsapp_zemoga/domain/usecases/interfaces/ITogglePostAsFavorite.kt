package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

interface ITogglePostAsFavorite {
    suspend fun invoke(post: PostsResponse): Result<List<FavoritePosts>?>
}
