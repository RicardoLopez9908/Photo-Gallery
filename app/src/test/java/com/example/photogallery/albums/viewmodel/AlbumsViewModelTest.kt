package com.example.photogallery.albums.viewmodel

import com.example.photogallery.presentation.albums.viewmodel.AlbumsViewModel
import com.example.photogallery.util.Response
import com.example.photogallery.utils.MainDispatcherRule
import com.example.photogallery.utils.Mocks
import com.example.photogallery.utils.RepositoryErrorMock
import com.example.photogallery.utils.RepositorySuccessMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AlbumsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: AlbumsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_success_init_block_and_get_albums_calls() {
        viewModel = AlbumsViewModel(RepositorySuccessMock())

        runTest {
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Success).data == Mocks.getAlbumList())
            viewModel.getAlbums()
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Success).data == Mocks.getAlbumList())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_error_init_block_and_get_albums_calls() {
        viewModel = AlbumsViewModel(RepositoryErrorMock())

        runTest {
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Error).exception.message == Mocks.EXCEPTION_MESSAGE)
            viewModel.getAlbums()
            assert(viewModel.screenContent.value is Response.Loading)
            advanceUntilIdle()
            assert((viewModel.screenContent.value as Response.Error).exception.message == Mocks.EXCEPTION_MESSAGE)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_success_init_block_and_get_is_dark_theme_call() {
        val repository = RepositorySuccessMock()
        viewModel = AlbumsViewModel(repository)

        runTest {
            assert(viewModel.isDarkTheme.value == null)
            repository.isDarkTheme = true
            advanceUntilIdle()
            assert(viewModel.isDarkTheme.value == repository.isDarkTheme)
            viewModel.saveIsDarkThemeValue(false)
            advanceUntilIdle()
            assert((repository.isDarkTheme == false))
        }
    }
}
