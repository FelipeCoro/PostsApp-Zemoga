package com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse

import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetPostById
import io.mockk.mockk

class FakeIGetPostById:IGetPostById {
    val mockkPostResponse = mockk<PostsResponse>()
    override suspend fun invoke(postId: Int): Result<PostsResponse?> {
      return Result.success(mockkPostResponse)
    }

}
