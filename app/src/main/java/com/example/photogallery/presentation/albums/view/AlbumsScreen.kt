package com.example.photogallery.presentation.albums.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.photogallery.presentation.albums.viewmodel.AlbumsViewModel

@Composable
fun AlbumsScreen(viewModel: AlbumsViewModel = hiltViewModel()) {
    val state = viewModel.screenContent.collectAsState()
    Text(text = state.value.toString())
}
