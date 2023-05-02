package com.example.photogallery.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.photogallery.presentation.albums.view.AlbumsScreen
import com.example.photogallery.presentation.albums.viewmodel.AlbumsViewModel
import com.example.photogallery.presentation.main.navigation.NavigationConstants.ALBUM_ID
import com.example.photogallery.presentation.main.navigation.NavigationConstants.ENCODED_URL
import com.example.photogallery.presentation.main.navigation.NavigationConstants.PHOTO_TITLE
import com.example.photogallery.presentation.photo.view.PhotoDetailScreen
import com.example.photogallery.presentation.photos.view.PhotosScreen
import com.example.photogallery.presentation.photos.viewmodel.PhotosViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    changeThemeColor: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Albums.route
    ) {
        composable(route = Screen.Albums.route) {
            val viewModel = hiltViewModel<AlbumsViewModel>()
            val state = viewModel.screenContent.collectAsState()

            AlbumsScreen(
                onTapRetry = viewModel::getAlbums,
                response = state.value,
                changeThemeColor = changeThemeColor,
                navigateToPhotos = { albumId ->
                    navController.navigate("${Screen.Photos.route}/$albumId")
                }
            )
        }
        composable(
            route = "${Screen.Photos.route}/{$ALBUM_ID}",
            arguments = listOf(navArgument(ALBUM_ID) { type = NavType.IntType })
        ) {
            val viewModel = hiltViewModel<PhotosViewModel>()
            val state = viewModel.screenContent.collectAsState()

            PhotosScreen(
                onTapBack = navController::popBackStack,
                onTapRetry = viewModel::getPhotos,
                response = state.value,
                changeThemeColor = changeThemeColor,
                navigateToPhotoDetail = { url, title ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate("${Screen.PhotoDetail.route}/$encodedUrl/$title")
                }
            )
        }
        composable(
            route = "${Screen.PhotoDetail.route}/{$ENCODED_URL}/{$PHOTO_TITLE}",
            arguments = listOf(
                navArgument(ENCODED_URL) { type = NavType.StringType },
                navArgument(PHOTO_TITLE) { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val photoUrl = navBackStackEntry.arguments?.getString(ENCODED_URL)
            val title = navBackStackEntry.arguments?.getString(PHOTO_TITLE)

            PhotoDetailScreen(
                onTapBack = navController::popBackStack,
                title = title,
                photoUrl = photoUrl ?: ""
            )
        }
    }
}
