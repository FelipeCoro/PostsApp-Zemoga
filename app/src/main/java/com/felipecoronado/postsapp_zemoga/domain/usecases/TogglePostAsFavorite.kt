package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.ITogglePostAsFavorite
import javax.inject.Inject

class TogglePostAsFavorite @Inject constructor(
    private val postsRepository: IPostsRepository
) : ITogglePostAsFavorite {
    override suspend fun invoke(post: PostsResponse): Result<List<FavoritePosts>?> {
        return postsRepository.togglePostAsFavorite(post)
    }
}
