package com.felipecoronado.postsapp_zemoga.data.webservice

import retrofit2.Response
import retrofit2.http.GET

interface PostsProviderService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostsResponse>>
}
