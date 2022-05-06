package com.felipecoronado.postsapp_zemoga.data.webservice.dtos

data class CommentsResponse(
    val postId:Int,
    val id:Int,
    val name: String,
    val email: String,
    val body:String
)
