package com.example.photogallery.data.di

import com.example.photogallery.data.repository.PhotoGalleryRepositoryImpl
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPhotoGalleryRepository(
        photoGalleryRepositoryImpl: PhotoGalleryRepositoryImpl
    ): PhotoGalleryRepository
}
