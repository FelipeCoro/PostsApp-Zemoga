package com.felipecoronado.postsapp_zemoga.data.dimodules

import android.content.Context
import androidx.room.Room
import com.felipecoronado.postsapp_zemoga.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PostDatabase::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectArtDao(database: PostDatabase) = database.favoritePostDao()
}
