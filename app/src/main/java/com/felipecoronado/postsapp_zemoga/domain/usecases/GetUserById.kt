package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetUserById
import javax.inject.Inject

class GetUserById @Inject constructor(
    private val postsRepository: IPostsRepository
) : IGetUserById {

    override suspend fun invoke(userId: Int): Result<UsersResponse?> =
        postsRepository.getUserById(userId)
}

