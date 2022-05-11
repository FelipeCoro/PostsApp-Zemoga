package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

interface IGetAllPostsList {
    suspend fun invoke(): Result<MutableList<PostsResponse>>
}
