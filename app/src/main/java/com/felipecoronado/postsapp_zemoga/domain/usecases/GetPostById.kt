package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetPostById
import javax.inject.Inject

class GetPostById @Inject constructor(
    private val postsRepository: IPostsRepository
) : IGetPostById {

    override suspend fun invoke(postId: Int): Result<PostsResponse?> =
        postsRepository.getPostById(postId)
}


