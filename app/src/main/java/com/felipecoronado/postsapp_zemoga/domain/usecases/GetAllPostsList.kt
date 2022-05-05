package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import javax.inject.Inject


class GetAllPostsList  @Inject constructor(
    private val postsRepository: IPostsRepository
): IGetAllPostsList {

    override suspend fun invoke(): Result<List<PostsResponse>> {
        val result = postsRepository.getPosts()

        return if (result.isFailure) {
            Result.failure(result.exceptionOrNull() ?: Exception())
        } else {
            result
        }
    }
}
