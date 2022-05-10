package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetCommentsById
import javax.inject.Inject

class GetCommentsById @Inject constructor(
    private val postsRepository: IPostsRepository
) : IGetCommentsById {

    override suspend fun invoke(postId:Int): Result<List<CommentsResponse>> {
        val result = postsRepository.getCommentsByPostId(postId)

        return if (result.isFailure) {
            Result.failure(Exception())
        } else {
            result
        }
    }
}

