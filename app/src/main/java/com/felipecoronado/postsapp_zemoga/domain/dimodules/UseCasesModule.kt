package com.felipecoronado.postsapp_zemoga.domain.dimodules

import com.felipecoronado.postsapp_zemoga.domain.usecases.GetAllPostsList
import com.felipecoronado.postsapp_zemoga.domain.usecases.GetCommentsById
import com.felipecoronado.postsapp_zemoga.domain.usecases.GetPostById
import com.felipecoronado.postsapp_zemoga.domain.usecases.GetUserById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetCommentsById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetPostById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetUserById
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
}
