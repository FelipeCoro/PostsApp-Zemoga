package com.felipecoronado.postsapp_zemoga.data.webservice.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostsResponse (
    val userId:Int,
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val title: String,
    val body:String,
    var favorite:Boolean = false
)
