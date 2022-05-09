package com.felipecoronado.postsapp_zemoga.domain.dimodules

import com.felipecoronado.postsapp_zemoga.domain.usecases.*
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindsGetAllPostList(useCase: GetAllPostsList): IGetAllPostsList

    @Binds
    abstract fun bindsGetPostById(useCase: GetPostById): IGetPostById

    @Binds
    abstract fun bindsGetUserById(useCase: GetUserById): IGetUserById

    @Binds
    abstract fun bindsGetCommentsById(useCase: GetCommentsById): IGetCommentsById

    @Binds
    abstract fun bindsTogglePostAsFavorite(useCase:TogglePostAsFavorite):ITogglePostAsFavorite
}
