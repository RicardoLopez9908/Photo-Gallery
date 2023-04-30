package com.example.photogallery.presentation.albums.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.photogallery.R
import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.presentation.base.view.BaseScreenHandler
import com.example.photogallery.presentation.base.view.BasicToolbar
import com.example.photogallery.presentation.base.view.ChangeThemeButton
import com.example.photogallery.util.Response

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UNCHECKED_CAST")
@Composable
fun AlbumsScreen(
    navController: NavHostController,
    response: Response<Any?>,
    onClickRetry: () -> Unit,
    changeThemeColor: () -> Unit
) {
    BaseScreenHandler(
        onClickRetry = onClickRetry,
        response = response
    ) {
        val albumList = (response as Response.Success<List<AlbumData>>).data
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                BasicToolbar(
                    scrollBehavior = scrollBehavior,
                    title = stringResource(id = R.string.albums_title),
                    menuContent = {
                        ChangeThemeButton(changeThemeColor)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = dimensionResource(id = R.dimen.base_horizontal_padding))
            ) {
                albumList?.forEach {
                    Text(text = it.title)
                }
            }
        }
    }
}
