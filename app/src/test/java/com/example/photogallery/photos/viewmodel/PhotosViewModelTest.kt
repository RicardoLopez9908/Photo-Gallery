package com.example.photogallery.photos.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.photogallery.presentation.main.navigation.NavigationConstants
import com.example.photogallery.presentation.photos.viewmodel.PhotosViewModel
import com.example.photogallery.util.Response
import com.example.photogallery.util.ViewModelException
import com.example.photogallery.utils.MainDispatcherRule
import com.example.photogallery.utils.Mocks
import com.example.photogallery.utils.RepositoryErrorMock
import com.example.photogallery.utils.RepositorySuccessMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: PhotosViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_success_init_block_and_get_photos_calls() {
        val albumIdExample = 1234

        viewModel = PhotosViewModel(
            RepositorySuccessMock(),
            savedStateHandle = SavedStateHandle(mapOf(NavigationConstants.ALBUM_ID to albumIdExample))
        )

        assert(viewModel.albumId == albumIdExample)

        runTest {
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Success).data == Mocks.getPhotoList())
            viewModel.getPhotos()
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Success).data == Mocks.getPhotoList())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_get_photos_calls_without_album_id_should_be_error() {
        viewModel = PhotosViewModel(
            RepositorySuccessMock(),
            savedStateHandle = SavedStateHandle()
        )

        runTest {
            viewModel.getPhotos()
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Error).exception is ViewModelException)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_error_init_block_and_get_photos_calls() {
        val albumIdExample = 1234

        viewModel = PhotosViewModel(
            RepositoryErrorMock(),
            savedStateHandle = SavedStateHandle(mapOf(NavigationConstants.ALBUM_ID to albumIdExample))
        )

        assert(viewModel.albumId == albumIdExample)

        runTest {
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Error).exception.message == Mocks.EXCEPTION_MESSAGE)
            viewModel.getPhotos()
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Error).exception.message == Mocks.EXCEPTION_MESSAGE)
        }
    }
}
