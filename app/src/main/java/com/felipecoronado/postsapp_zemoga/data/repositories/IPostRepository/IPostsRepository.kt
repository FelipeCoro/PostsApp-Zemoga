package com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse

interface IPostsRepository {
    suspend fun getPosts(): Result<List<PostsResponse>>
    suspend fun getPostById(postId:Int): Result<PostsResponse?>
    suspend fun getUserById(userId:Int): Result<UsersResponse?>
    suspend fun getCommentsByPostId(postId: Int): Result<List<CommentsResponse>>
}
