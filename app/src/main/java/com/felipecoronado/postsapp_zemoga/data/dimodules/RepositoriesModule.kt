package com.felipecoronado.postsapp_zemoga.data.dimodules

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.repositories.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule  {
    @Binds
    abstract fun bindsPostsRepository(repository: PostsRepository): IPostsRepository

}
