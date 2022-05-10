package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllFavoritesPostsList
import javax.inject.Inject

class GetAllFavoritesPostsList @Inject constructor(
    private val postsRepository: IPostsRepository
): IGetAllFavoritesPostsList {
    override suspend fun invoke(): Result<List<FavoritePosts>> {
        val result = postsRepository.getFavoritePosts()
        return if (result.isFailure) {
            Result.failure(Exception())
        } else {
            result
        }
    }
}
