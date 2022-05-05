package com.felipecoronado.postsapp_zemoga.data.utils

data class NetworkException(
    val statusCode: Int,
    override val message: String,
    val errorBody: String = "",
    val values: HashMap<String, String> = hashMapOf()
) : Exception(message)
