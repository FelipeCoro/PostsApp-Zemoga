package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse

interface IGetAllPostsList {
    suspend fun invoke(): Result<List<PostsResponse>>
}
