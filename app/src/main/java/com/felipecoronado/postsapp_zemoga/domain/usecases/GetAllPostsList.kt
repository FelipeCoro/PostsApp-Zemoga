package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.utils.MaperPost
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import javax.inject.Inject


class GetAllPostsList @Inject constructor(
    private val postsRepository: IPostsRepository
) : IGetAllPostsList {

    override suspend fun invoke(): Result<MutableList<PostsResponse>> {
        val resultAllPostList = postsRepository.getPosts()
        val resultFavoritePostList = postsRepository.getFavoritePosts()
        var favoriteList = resultFavoritePostList.getOrNull()

        if (favoriteList == null) {
            favoriteList = listOf()
        }

        return if (resultAllPostList.isFailure) {
            Result.failure(Exception())
        } else {
            val allPosts = resultAllPostList.getOrNull()
                ?.filter { it.id !in favoriteList.map { favorite -> favorite.id } } ?: listOf()

            val finalList = mutableListOf<PostsResponse>()
            finalList.addAll(favoriteList.map {
                MaperPost.fromFavoriteToPostResponse(it)
            })

            finalList.addAll(allPosts)
            return Result.success(finalList)
        }
    }

}
