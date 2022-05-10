package com.felipecoronado.postsapp_zemoga.data.repositories

import com.felipecoronado.postsapp_zemoga.data.database.room.daos.FavoritePostsDao
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
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
    private val postsProvider: PostsProviderService,
    private val favoritePostsDao: FavoritePostsDao
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

    override suspend fun getUserById(userId: Int): Result<UsersResponse?> {
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
                    val comment = response.body() ?: listOf()
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

    override suspend fun togglePostAsFavorite(post: PostsResponse): Result<List<FavoritePosts>?> {
        return when {
            favoritePostsDao.getFavoritePostFromList(post.id) == null -> {
                val favoritePost = FavoritePosts(post.userId, post.id, post.title, post.body)
                favoritePostsDao.addPost(favoritePost)
                Result.success(favoritePostsDao.getFavoritePostList())
            }
            favoritePostsDao.getFavoritePostFromList(post.id) != null -> {
                favoritePostsDao.removePost(post.id)
                Result.success(favoritePostsDao.getFavoritePostList())
            }
            else -> {
                Result.failure(Exception("Cool Enums Exception Utils Class"))
            }
        }
    }

    override suspend fun getFavoritePosts(): Result<List<FavoritePosts>> {
        return withContext(Dispatchers.Main) {

            val favoriteList = favoritePostsDao.getFavoritePostList()
            when {
                favoriteList.isNullOrEmpty() -> Result.failure(Exception("No post saved as favorite"))
                else -> {
                    Result.success(favoriteList)
                }
            }

        }
    }
}
