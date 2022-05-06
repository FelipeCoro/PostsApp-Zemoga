package com.felipecoronado.postsapp_zemoga.data.repositories

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.utils.NetworkException
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsProviderService
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsProvider: PostsProviderService
) : IPostsRepository {

    override suspend fun getPosts(): Result<List<PostsResponse>> {
        return withContext(Dispatchers.IO) {
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

    override suspend fun getPostById(postId: Int): Result<PostsResponse?> {
        return withContext(Dispatchers.IO) {
            val response = postsProvider.getPostById(postId)
            when {
                response.isSuccessful -> {
                    val post = response.body()
                    Result.success(post)
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

    override suspend fun getUserById(userId:Int): Result<UsersResponse?> {
        return withContext(Dispatchers.IO) {
            val response = postsProvider.getUserById(userId)
            when {
                response.isSuccessful -> {
                    val user = response.body()
                    Result.success(user)
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

    override suspend fun getCommentsByPostId(postId: Int): Result<List<CommentsResponse>> {
        return withContext(Dispatchers.IO) {
            val response = postsProvider.getCommentsByPostId(postId)
            when {
                response.isSuccessful -> {
                    val comment = response.body()?: listOf()
                    Result.success(comment)
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




