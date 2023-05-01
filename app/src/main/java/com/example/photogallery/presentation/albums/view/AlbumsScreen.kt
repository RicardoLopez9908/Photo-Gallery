package com.example.photogallery.presentation.albums.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.photogallery.R
import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.presentation.base.view.BaseScreenStateHandler
import com.example.photogallery.presentation.base.view.CustomAsyncCardImage
import com.example.photogallery.presentation.base.view.DefaultScrollableScreenWithToolbar
import com.example.photogallery.presentation.base.view.DefaultSearch
import com.example.photogallery.util.Response

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UNCHECKED_CAST")
@Composable
fun AlbumsScreen(
    navController: NavHostController,
    response: Response<Any?>,
    onClickRetry: () -> Unit,
    changeThemeColor: () -> Unit
) {
    BaseScreenStateHandler(
        onClickRetry = onClickRetry,
        response = response
    ) {
        val albumList = (response as Response.Success<List<AlbumData>>).data
        var searchState by remember { mutableStateOf("") }
        val albumListFiltered = if (searchState.isEmpty()) {
            albumList
        } else {
            albumList?.filter {
                it.title.contains(searchState)
            }
        }
        DefaultScrollableScreenWithToolbar(
            title = stringResource(id = R.string.albums_title),
            changeThemeColor = changeThemeColor,
            onClickRetry = onClickRetry
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                DefaultSearch(
                    value = searchState,
                    onValueChange = {
                        searchState = it
                    }
                )
            }
            if (albumListFiltered.isNullOrEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Text(
                        text = stringResource(id = R.string.empty_albums),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.text_feedback_top_padding)),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(albumListFiltered) { item ->
                    item.photoDataList?.first()?.thumbnailUrl?.let { thumbnailUrl ->
                        CustomAsyncCardImage(
                            onClick = { /*TODO*/ },
                            title = item.title,
                            thumbnailUrl = thumbnailUrl
                        )
                    }
                }
            }
        }
    }
}
