package com.example.photogallery.presentation.photo.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photogallery.R
import com.example.photogallery.presentation.base.view.DefaultToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    onTapBack: () -> Unit,
    title: String?,
    photoUrl: String
) {
    val localDensity = LocalDensity.current
    var imageWidth by remember { mutableStateOf(0.dp) }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            DefaultToolbar(onTapBack = onTapBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.base_horizontal_padding))
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            imageWidth = with(localDensity) { coordinates.size.width.toDp() }
                        },
                    contentScale = ContentScale.FillWidth,
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false },
                    onError = { isLoading = false },
                    error = painterResource(id = R.drawable.ic_image_error)
                )
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(imageWidth),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            title?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.text_title_top_padding)),
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}
