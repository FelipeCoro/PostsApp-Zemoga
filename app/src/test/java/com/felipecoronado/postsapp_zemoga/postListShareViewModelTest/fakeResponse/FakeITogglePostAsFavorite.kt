package com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.ITogglePostAsFavorite

class FakeITogglePostAsFavorite : ITogglePostAsFavorite {
    override suspend fun invoke(post: PostsResponse): Result<List<FavoritePosts>?> {
        return Result.success(listOf())
    }
}

