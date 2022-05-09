package com.felipecoronado.postsapp_zemoga.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.felipecoronado.postsapp_zemoga.data.database.room.daos.FavoritePostsDao
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.utils.DataConverter
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse

@TypeConverters(DataConverter::class)
@Database(entities = [FavoritePosts::class, PostsResponse::class ], version=1)
abstract class PostDatabase: RoomDatabase() {
    abstract fun favoritePostDao():FavoritePostsDao
}
