package com.felipecoronado.postsapp_zemoga.data.webservice.dtos

data class PostsResponse (
    val userId:Int,
    val id:Int,
    val title: String,
    val body:String
)
