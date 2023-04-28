package com.example.photogallery.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.photogallery.presentation.albums.view.AlbumsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Albums.route
    ) {
        composable(route = Screen.Albums.route) {
            AlbumsScreen()
        }
    }
}
