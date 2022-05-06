package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse

interface IGetUserById {
    suspend fun invoke(userId: Int): Result<UsersResponse?>
}
