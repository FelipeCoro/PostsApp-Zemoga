package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

interface IGetPostById {
    suspend fun invoke(postId:Int): Result<PostsResponse?>
}
