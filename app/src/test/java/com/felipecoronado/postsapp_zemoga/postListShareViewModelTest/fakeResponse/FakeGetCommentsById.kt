package com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetCommentsById
import io.mockk.mockk

class FakeGetCommentsById:IGetCommentsById {
    private val mockCommentsResponse = mockk<List<CommentsResponse>>()
    override suspend fun invoke(postId: Int): Result<List<CommentsResponse>> {
        return Result.success(mockCommentsResponse)
    }

}
