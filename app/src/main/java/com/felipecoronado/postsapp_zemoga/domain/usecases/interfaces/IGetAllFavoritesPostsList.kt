package com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces

import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts

interface IGetAllFavoritesPostsList {
    suspend fun invoke(): Result<List<FavoritePosts>>
}
