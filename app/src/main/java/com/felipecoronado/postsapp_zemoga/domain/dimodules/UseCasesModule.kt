package com.felipecoronado.postsapp_zemoga.domain.dimodules

import com.felipecoronado.postsapp_zemoga.domain.usecases.GetAllPostsList
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindsGetAllPostList(useCase: GetAllPostsList): IGetAllPostsList
}
