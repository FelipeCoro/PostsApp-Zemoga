package com.felipecoronado.postsapp_zemoga.data.database.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favoritePosts_table")
data class FavoritePosts(
    val userId:Int,
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val title: String,
    val body:String
)
