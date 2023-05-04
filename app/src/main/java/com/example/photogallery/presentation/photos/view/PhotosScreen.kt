package com.example.photogallery.presentation.photos.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.photogallery.R
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.presentation.base.view.BaseScreenStateHandler
import com.example.photogallery.presentation.base.view.CustomAsyncCardImage
import com.example.photogallery.presentation.base.view.DefaultScrollableScreenWithToolbar
import com.example.photogallery.presentation.base.view.DefaultSearch
import com.example.photogallery.util.Response

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(
    onTapBack: () -> Unit,
    response: Response<Any?>,
    onTapRetry: () -> Unit,
    changeThemeColor: (Boolean) -> Unit,
    navigateToPhotoDetail: (url: String, title: String) -> Unit
) {
    BaseScreenStateHandler(
        onTapRetry = onTapRetry,
        response = response
    ) { successData ->
        val photoList = if (successData is List<*>) successData.filterIsInstance<PhotoData>() else emptyList()
        var searchState by rememberSaveable { mutableStateOf("") }
        val photoListFiltered = if (searchState.isEmpty()) {
            photoList
        } else {
            photoList.filter {
                it.title.contains(searchState, ignoreCase = true)
            }
        }
        DefaultScrollableScreenWithToolbar(
            title = stringResource(id = R.string.photos_title),
            changeThemeColor = changeThemeColor,
            onTapRetry = onTapRetry,
            onTapBack = onTapBack,
            columns = 3
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                DefaultSearch(
                    value = searchState,
                    onValueChange = {
                        searchState = it
                    }
                )
            }
            if (photoListFiltered.isEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Text(
                        text = stringResource(id = R.string.empty_photos),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.text_feedback_top_padding)),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(photoListFiltered) { item ->
                    CustomAsyncCardImage(
                        onClick = { navigateToPhotoDetail(item.url, item.title) },
                        title = item.title,
                        thumbnailUrl = item.thumbnailUrl,
                        shape = CutCornerShape(0.dp),
                        titleMaxLines = 2
                    )
                }
            }
        }
    }
}
