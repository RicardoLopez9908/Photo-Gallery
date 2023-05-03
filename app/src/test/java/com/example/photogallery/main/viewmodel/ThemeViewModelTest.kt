package com.example.photogallery.main.viewmodel

import com.example.photogallery.presentation.main.viewmodel.ThemeViewModel
import com.example.photogallery.utils.MainDispatcherRule
import com.example.photogallery.utils.RepositorySuccessMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ThemeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ThemeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun check_success_init_block_and_get_is_dark_theme_call() {
        val repository = RepositorySuccessMock()
        viewModel = ThemeViewModel(repository)

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
