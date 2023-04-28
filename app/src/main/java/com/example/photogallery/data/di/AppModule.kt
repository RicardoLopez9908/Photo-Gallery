package com.example.photogallery.data.di

import com.example.photogallery.data.api.PhotoGalleryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotoGalleryApi(): PhotoGalleryApi =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // todo: create constant to save this value
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoGalleryApi::class.java)
}
