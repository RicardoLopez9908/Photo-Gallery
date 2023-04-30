package com.example.photogallery.data.di

import android.content.Context
import com.example.photogallery.PhotoGalleryApp
import com.example.photogallery.data.api.PhotoGalleryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): PhotoGalleryApp =
        app as PhotoGalleryApp
}
