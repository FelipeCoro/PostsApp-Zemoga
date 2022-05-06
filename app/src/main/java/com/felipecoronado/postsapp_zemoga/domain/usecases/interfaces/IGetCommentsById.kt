package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse

interface IGetCommentsById {
    suspend fun invoke(postId:Int): Result<List<CommentsResponse>>
}
