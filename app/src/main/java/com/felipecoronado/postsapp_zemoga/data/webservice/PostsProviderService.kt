package com.felipecoronado.postsapp_zemoga.data.webservice

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsProviderService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostsResponse>>

    @GET("posts/{post-id}")
    suspend fun getPostById(@Path("post-id") postId: Int): Response<PostsResponse>

    @GET("users/{user-id}")
    suspend fun getUserById(@Path("user-id") userId: Int): Response<UsersResponse>

    @GET("posts/{post-id}/comments")
    suspend fun getCommentsByPostId(@Path("post-id") postId: Int): Response<List<CommentsResponse>>
}
