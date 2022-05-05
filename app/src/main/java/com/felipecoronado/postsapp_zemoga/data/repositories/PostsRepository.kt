package com.felipecoronado.postsapp_zemoga.data.repositories

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.utils.NetworkException
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsProviderService
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsProvider: PostsProviderService
) : IPostsRepository {

    override suspend fun getPosts(): Result<List<PostsResponse>> {
        return withContext(Dispatchers.IO){
            val response = postsProvider.getPosts()
            when {
                response.isSuccessful -> {
                    val posts = response.body() ?: listOf()
                    Result.success(posts)
                }
                else -> {
                    Result.failure(
                        NetworkException(
                            response.code(),
                            response.message(),
                            response.errorBody().toString()
                        )
                    )
                }
            }
        }
    }
}
