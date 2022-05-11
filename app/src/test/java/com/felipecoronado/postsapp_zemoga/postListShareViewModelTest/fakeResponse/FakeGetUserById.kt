package com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetUserById
import io.mockk.mockk

class FakeGetUserById:IGetUserById {
    val mockUserResponse = mockk<UsersResponse>()

    override suspend fun invoke(userId: Int): Result<UsersResponse?> {
        return Result.success(mockUserResponse)
    }

}
