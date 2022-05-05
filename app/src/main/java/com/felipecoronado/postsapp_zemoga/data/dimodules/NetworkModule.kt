package com.felipecoronado.postsapp_zemoga.data.dimodules

import com.felipecoronado.postsapp_zemoga.data.utils.Constants.BASE_URL
import com.felipecoronado.postsapp_zemoga.data.webservice.PostsProviderService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun injectRetrofitAPI(): PostsProviderService {

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(PostsProviderService::class.java)
    }
}
