package com.example.photogallery.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.photogallery.presentation.albums.view.AlbumsScreen
import com.example.photogallery.presentation.albums.viewmodel.AlbumsViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    changeThemeColor: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Albums.route
    ) {
        composable(route = Screen.Albums.route) {
            val viewModel = hiltViewModel<AlbumsViewModel>()
            val state = viewModel.screenContent.collectAsState()

            AlbumsScreen(
                navController = navController,
                onClickRetry = viewModel::getAlbums,
                response = state.value,
                changeThemeColor = changeThemeColor
            )
        }
    }
}
