package com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository

import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse

interface IPostsRepository {
    suspend fun getPosts(): Result<List<PostsResponse>>
}
