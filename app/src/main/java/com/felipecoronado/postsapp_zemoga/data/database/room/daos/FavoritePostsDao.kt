package com.felipecoronado.postsapp_zemoga.data.database.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

@Dao
interface FavoritePostsDao {
    @Insert
    suspend fun addPost(post:FavoritePosts):Long

    @Query("DELETE FROM favoritePosts_table WHERE id = :postId")
    suspend fun removePost(postId:Int)

    @Query("SELECT * FROM favoritePosts_table")
    suspend fun getFavoritePostList() : List<FavoritePosts>?

    @Query("SELECT * FROM favoritePosts_table WHERE id = :postId")
    suspend fun getFavoritePostFromList(postId:Int) : FavoritePosts?


}
